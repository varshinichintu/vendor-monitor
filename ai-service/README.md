# AI Service — Vendor Performance Monitor

A Flask-based AI microservice powered by Groq (LLaMA-3.3-70b) for vendor performance analysis.

## Tech Stack
- Python 3.11
- Flask 3.0
- Groq API (LLaMA-3.3-70b-versatile)
- Redis (optional caching)
- flask-limiter

## Prerequisites
- Python 3.11 installed
- Groq API key from console.groq.com
- Redis (optional)

## Setup

1. Install dependencies
pip install -r requirements.txt

2. Create .env file
GROQ_API_KEY=your_groq_api_key_here

3. Run the service
python app.py

Service runs on http://localhost:5000

## Environment Variables

GROQ_API_KEY - Your Groq API key from console.groq.com - Required

## API Reference

### GET /health
Returns service health status.

Response:
{
  "status": "ok",
  "service": "ai-service",
  "model": "llama-3.3-70b-versatile",
  "uptime_seconds": 133,
  "avg_response_time_ms": 1539.6,
  "redis_connected": false,
  "total_requests": 1
}

### POST /describe
Returns AI-generated vendor performance description.

Request Body:
{
  "vendor_name": "TechSupply Co",
  "category": "Electronics",
  "performance_score": 92,
  "delivery_rate": 98,
  "quality_rating": 4.5,
  "contract_value": 50000
}

Response:
{
  "vendor_name": "TechSupply Co",
  "analysis": "TechSupply Co demonstrates exceptional performance...",
  "is_fallback": false,
  "generated_at": "2026-04-26T10:26:08Z"
}

### POST /recommend
Returns 3 AI-generated recommendations.

Request Body:
{
  "vendor_name": "TechSupply Co",
  "category": "Electronics",
  "performance_score": 92,
  "delivery_rate": 98,
  "quality_rating": 4.5,
  "contract_value": 50000
}

Response:
{
  "vendor_name": "TechSupply Co",
  "recommendations": [
    {
      "action_type": "reward",
      "description": "Consider offering contract renewal...",
      "priority": "high"
    },
    {
      "action_type": "monitor",
      "description": "Track delivery rate monthly...",
      "priority": "medium"
    },
    {
      "action_type": "improve",
      "description": "Request quality improvement plan...",
      "priority": "low"
    }
  ],
  "is_fallback": false,
  "generated_at": "2026-04-26T10:26:08Z"
}

### POST /generate-report
Returns a full AI-generated performance report.

Request Body:
{
  "vendor_name": "TechSupply Co",
  "category": "Electronics",
  "performance_score": 92,
  "delivery_rate": 98,
  "quality_rating": 4.5,
  "contract_value": 50000,
  "report_period": "Q1 2026"
}

Response:
{
  "vendor_name": "TechSupply Co",
  "report_period": "Q1 2026",
  "report": {
    "title": "Q1 2026 Vendor Performance Report for TechSupply Co",
    "summary": "TechSupply Co has demonstrated exceptional performance...",
    "overview": "During Q1 2026, TechSupply Co has shown remarkable...",
    "key_items": [
      "Achieved performance score of 92",
      "Maintained delivery rate of 98%",
      "Received quality rating of 4.5"
    ],
    "recommendations": [
      "Continue monitoring delivery rates",
      "Develop quality improvement strategies",
      "Explore contract expansion opportunities"
    ]
  },
  "is_fallback": false,
  "generated_at": "2026-04-26T10:26:08Z"
}

## Error Handling

400 - Missing or empty required field
200 with is_fallback true - AI unavailable, default response returned

## Security
- X-Content-Type-Options header
- X-Frame-Options header
- X-XSS-Protection header
- Strict-Transport-Security header
- Content-Security-Policy header
- Referrer-Policy header