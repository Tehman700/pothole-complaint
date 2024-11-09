# image_classifier.py
import base64
from flask import Flask, request, jsonify
from openai import OpenAI

app = Flask(__name__)
client = OpenAI()

def classify_image(image_base64):
    response = client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[
            {
                "role": "user",
                "content": [
                    {
                        "type": "text",
                        "text": "What is in this image?",
                    },
                    {
                        "type": "image_url",
                        "image_url": {
                            "url": f"data:image/jpeg;base64,{image_base64}"
                        },
                    },
                ],
            }
        ],
    )
    return response.choices[0].message['content']

@app.route('/classify', methods=['POST'])
def classify():
    data = request.json
    image_base64 = data['imageBase64']
    classification = classify_image(image_base64)
    is_road = "road" in classification.lower()
    return jsonify({'classification': classification, 'is_road': is_road})

if __name__ == '__main__':
    app.run(port=5000)
