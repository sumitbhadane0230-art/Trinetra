import math
from collections import defaultdict

import numpy as np
import pandas as pd


# ============================================================
# TRINETRA PERSONNEL FEATURE ENGINEERING
# ============================================================

def normalize(value, lower, upper):
    return float(
        np.clip(
            (value - lower) / (upper - lower),
            0,
            1
        )
    )


def positive_pressure(value, lower, upper):
    normalized = (value - lower) / (upper - lower)
    return float(np.clip(normalized, 0, 1))


def calculate_trend(values):
    """
    Same 4-point linear trend logic used by the
    TRINETRA feature-engineering notebook.
    """

    if len(values) < 4:
        return None

    series = np.asarray(values[-4:], dtype=float)

    if np.isnan(series).any():
        return None

    x = np.arange(len(series))

    return float(
        np.polyfit(x, series, 1)[0]
    )


def build_features(
    current_record,
    history,
    baseline
):
    """
    Build the exact 32 ML features expected by the
    existing TRINETRA models.

    current_record:
        Current week's raw personnel data.

    history:
        Previous/current raw check-in records.

    baseline:
        Personnel-specific baseline values.
    """

    record = dict(current_record)

    # --------------------------------------------------------
    # Baseline values
    # --------------------------------------------------------

    baseline_duty_hours = float(
        baseline["baseline_duty_hours"]
    )

    baseline_sleep_hours = float(
        baseline["baseline_sleep_hours"]
    )

    baseline_stress = float(
        baseline["baseline_stress"]
    )

    baseline_fatigue = float(
        baseline["baseline_fatigue"]
    )

    # --------------------------------------------------------
    # Deviations
    # --------------------------------------------------------

    duty_deviation = (
        record["duty_hours_week"]
        - baseline_duty_hours
    )

    sleep_deviation = (
        baseline_sleep_hours
        - record["avg_sleep_hours"]
    )

    stress_deviation = (
        record["self_reported_stress"]
        - baseline_stress
    )

    fatigue_deviation = (
        record["fatigue_level"]
        - baseline_fatigue
    )

    # --------------------------------------------------------
    # Workload pressures
    # --------------------------------------------------------

    duty_pressure = normalize(
        record["duty_hours_week"],
        35,
        70
    )

    overtime_pressure = normalize(
        record["overtime_hours"],
        0,
        14
    )

    night_pressure = normalize(
        record["night_shifts"],
        0,
        5
    )

    consecutive_pressure = normalize(
        record["consecutive_duty_days"],
        1,
        10
    )

    rest_pressure = 1 - normalize(
        record["avg_rest_hours"],
        5,
        16
    )

    training_pressure = normalize(
        record["training_hours"],
        0,
        8
    )

    deployment_pressure = normalize(
        record["current_deployment_days"],
        0,
        7
    )

    # --------------------------------------------------------
    # Workload index
    # --------------------------------------------------------

    workload_index = (
        0.25 * duty_pressure
        + 0.15 * overtime_pressure
        + 0.15 * night_pressure
        + 0.15 * consecutive_pressure
        + 0.15 * rest_pressure
        + 0.05 * training_pressure
        + 0.10 * deployment_pressure
    )

    # --------------------------------------------------------
    # Build historical series
    # --------------------------------------------------------

    records = list(history) + [record]

    workload_values = []

    for item in records:

        item_duty_pressure = normalize(
            item["duty_hours_week"],
            35,
            70
        )

        item_overtime_pressure = normalize(
            item["overtime_hours"],
            0,
            14
        )

        item_night_pressure = normalize(
            item["night_shifts"],
            0,
            5
        )

        item_consecutive_pressure = normalize(
            item["consecutive_duty_days"],
            1,
            10
        )

        item_rest_pressure = 1 - normalize(
            item["avg_rest_hours"],
            5,
            16
        )

        item_training_pressure = normalize(
            item["training_hours"],
            0,
            8
        )

        item_deployment_pressure = normalize(
            item["current_deployment_days"],
            0,
            7
        )

        item_workload = (
            0.25 * item_duty_pressure
            + 0.15 * item_overtime_pressure
            + 0.15 * item_night_pressure
            + 0.15 * item_consecutive_pressure
            + 0.15 * item_rest_pressure
            + 0.05 * item_training_pressure
            + 0.10 * item_deployment_pressure
        )

        workload_values.append(item_workload)

    stress_values = [
        float(item["self_reported_stress"])
        for item in records
    ]

    fatigue_values = [
        float(item["fatigue_level"])
        for item in records
    ]

    sleep_values = [
        float(item["avg_sleep_hours"])
        for item in records
    ]

    # --------------------------------------------------------
    # Four-week trends
    # --------------------------------------------------------

    workload_trend = calculate_trend(
        workload_values
    )

    stress_trend = calculate_trend(
        stress_values
    )

    fatigue_trend = calculate_trend(
        fatigue_values
    )

    sleep_trend = calculate_trend(
        sleep_values
    )

    # --------------------------------------------------------
    # Signal agreement
    # --------------------------------------------------------

    signal_agreement = None

    if all(
        value is not None
        for value in [
            workload_trend,
            stress_trend,
            fatigue_trend,
            sleep_trend
        ]
    ):

        workload_deteriorating = (
            workload_trend > 0.05
        )

        stress_deteriorating = (
            stress_trend > 0.05
        )

        fatigue_deteriorating = (
            fatigue_trend > 0.05
        )

        sleep_deteriorating = (
            sleep_trend < -0.05
        )

        signal_agreement = (
            sum([
                workload_deteriorating,
                stress_deteriorating,
                fatigue_deteriorating,
                sleep_deteriorating
            ]) / 4
        )

    # --------------------------------------------------------
    # Pressure features
    # --------------------------------------------------------

    stress_pressure = positive_pressure(
        stress_deviation,
        0,
        2
    )

    fatigue_pressure = positive_pressure(
        fatigue_deviation,
        0,
        2
    )

    stress_trend_pressure = (
        positive_pressure(
            stress_trend,
            0,
            0.5
        )
        if stress_trend is not None
        else None
    )

    # --------------------------------------------------------
    # First few check-ins do not have 4-week history.
    #
    # The trained models cannot accept NaN trend features,
    # so prediction should not be attempted until enough
    # history exists.
    # --------------------------------------------------------

    if any(
        value is None
        for value in [
            workload_trend,
            stress_trend,
            fatigue_trend,
            sleep_trend,
            signal_agreement,
            stress_trend_pressure
        ]
    ):
        return {
            "ready": False,
            "weeks_available": len(records),
            "required_weeks": 4
        }

    # --------------------------------------------------------
    # Exact ML feature set from model metadata
    # --------------------------------------------------------

    features = {
        "role_category":
            record["role_category"],

        "posting_type":
            record["posting_type"],

        "service_tenure_months":
            record["service_tenure_months"],

        "duty_hours_week":
            record["duty_hours_week"],

        "overtime_hours":
            record["overtime_hours"],

        "night_shifts":
            record["night_shifts"],

        "consecutive_duty_days":
            record["consecutive_duty_days"],

        "avg_rest_hours":
            record["avg_rest_hours"],

        "training_hours":
            record["training_hours"],

        "current_deployment_days":
            record["current_deployment_days"],

        "days_since_last_deployment":
            record["days_since_last_deployment"],

        "recent_deployment_count":
            record["recent_deployment_count"],

        "leave_days_taken":
            record["leave_days_taken"],

        "days_since_last_leave":
            record["days_since_last_leave"],

        "avg_sleep_hours":
            record["avg_sleep_hours"],

        "sleep_quality":
            record["sleep_quality"],

        "self_reported_stress":
            record["self_reported_stress"],

        "fatigue_level":
            record["fatigue_level"],

        "mood_score":
            record["mood_score"],

        "recovery_feeling":
            record["recovery_feeling"],

        "duty_deviation":
            duty_deviation,

        "sleep_deviation":
            sleep_deviation,

        "stress_deviation":
            stress_deviation,

        "fatigue_deviation":
            fatigue_deviation,

        "workload_index":
            workload_index,

        "workload_trend":
            workload_trend,

        "stress_trend":
            stress_trend,

        "fatigue_trend":
            fatigue_trend,

        "sleep_trend":
            sleep_trend,

        "signal_agreement":
            signal_agreement,

        "stress_pressure":
            stress_pressure,

        "fatigue_pressure":
            fatigue_pressure,

        "stress_trend_pressure":
            stress_trend_pressure
    }

    return {
        "ready": True,
        "weeks_available": len(records),
        "required_weeks": 4,
        "features": features
    }