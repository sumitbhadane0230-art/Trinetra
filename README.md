# TRINETRA

### Personnel Wellness, Risk Screening & Support Platform

TRINETRA is an AI-assisted personnel wellness and support platform designed to help identify emerging patterns related to **stress, fatigue, and burnout risk** through structured wellness data and machine-learning-based screening.

The system consists of:

- 📱 Android personnel application
- ⚙️ FastAPI backend
- 🧠 Trained ML models for stress, fatigue, and burnout risk
- 📊 Welfare risk policy engine
- 📈 Historical wellness analytics
- 💬 Conversational wellness support
- 🛡️ Privacy-aware welfare workflows

> **Important:** TRINETRA's ML outputs are welfare-risk screening categories and are **not medical diagnoses**.

---

# 🏗️ System Architecture

```text
                    TRINETRA
                        │
        ┌───────────────┴────────────────┐
        │                                │
        ▼                                ▼
 Android Personnel App              FastAPI Backend
        │                                │
        │ Retrofit                       │
        └───────────────► HTTP ◄─────────┘
                                         │
                                         ▼
                                Feature Processing
                                         │
                                         ▼
                                ML Prediction Layer
                                         │
                         ┌───────────────┼───────────────┐
                         ▼               ▼               ▼
                       Stress          Fatigue        Burnout
                       Model            Model           Model
                         │               │               │
                         └───────────────┼───────────────┘
                                         ▼
                                  Policy Engine
                                         │
                                         ▼
                              Overall Welfare Risk
                                         │
                              ┌──────────┼──────────┐
                              ▼          ▼          ▼
                             Low       Monitor     High
                                         │
                                         ▼
                                  Welfare Priority
