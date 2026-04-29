from groq import Groq
from dotenv import load_dotenv
import os

load_dotenv()
client = Groq(api_key=os.getenv("GROQ_API_KEY"))

test_vendors = [
    {"vendor_name": "TechSupply Co", "category": "Electronics", "performance_score": 92, "delivery_rate": 98, "quality_rating": 4.5, "contract_value": 50000},
    {"vendor_name": "QuickShip Ltd", "category": "Logistics", "performance_score": 65, "delivery_rate": 72, "quality_rating": 3.1, "contract_value": 20000},
    {"vendor_name": "BuildRight Inc", "category": "Construction", "performance_score": 78, "delivery_rate": 85, "quality_rating": 3.8, "contract_value": 150000},
    {"vendor_name": "FreshFoods", "category": "Catering", "performance_score": 45, "delivery_rate": 60, "quality_rating": 2.5, "contract_value": 10000},
    {"vendor_name": "SecureIT", "category": "Cybersecurity", "performance_score": 95, "delivery_rate": 100, "quality_rating": 4.9, "contract_value": 75000},
]

with open("prompts/describe_prompt.txt", "r") as f:
    prompt_template = f.read()

for vendor in test_vendors:
    prompt = prompt_template.format(**vendor)
    response = client.chat.completions.create(
        model="llama-3.3-70b-versatile",
        messages=[{"role": "user", "content": prompt}],
        temperature=0.3,
        max_tokens=500
    )
    print(f"\n--- {vendor['vendor_name']} ---")
    print(response.choices[0].message.content)