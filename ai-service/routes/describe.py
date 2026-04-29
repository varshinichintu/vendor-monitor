from flask import Blueprint, request, jsonify
from services.groq_client import call_groq

describe_bp = Blueprint("describe", __name__)

@describe_bp.route("/describe", methods=["POST"])
def describe():
    data = request.get_json()

    if not data or "input" not in data:
        return jsonify({"error": "Invalid input"}), 400

    user_input = data["input"]

    with open("prompts/describe_prompt.txt", "r") as f:
        template = f.read()

    final_prompt = template.replace("{input}", user_input)

    result = call_groq(final_prompt)

    if result is None:
        return jsonify({"error": "AI failed"}), 500

    return jsonify({
        "description": result,
        "generated_at": "now"
    })
@describe_bp.route("/describe-test", methods=["GET"])
def describe_test():
    user_input = "Vendor has late deliveries but good pricing"

    with open("prompts/describe_prompt.txt", "r") as f:
        template = f.read()

    final_prompt = template.replace("{input}", user_input)

    result = call_groq(final_prompt)

    return {
        "description": result,
        "generated_at": "now"
    }