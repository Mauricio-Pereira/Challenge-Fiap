from flask import Flask, request, jsonify
from flask_basicauth import BasicAuth
from textblob import TextBlob
from googletrans import Translator
from sklearn.linear_model import LinearRegression
import pickle

colunas = ['tamanho', 'ano', 'garagem']
modelo = pickle.load(open('modelo.sav', 'rb'))


app = Flask(__name__)
app.config['BASIC_AUTH_USERNAME'] = 'admin'
app.config['BASIC_AUTH_PASSWORD'] = '12345'

basic_auth = BasicAuth(app)

@app.route('/')
def home():
    return "Minha primeira API."

@app.route('/sentimento/<frase>')
@basic_auth.required
def sentimento(frase):
    tb = TextBlob(frase)
    translator = Translator()
    try:
        translation = translator.translate(tb.raw, dest='en')
        if translation is None:
            raise ValueError("Falha na tradução.")
        tb_en = translation.text
    except Exception as e:
        return str(e)
    tb_en_blob = TextBlob(tb_en)
    polarity = tb_en_blob.sentiment.polarity
    return "polaridade: {}".format(polarity)


@app.route('/cotacao/', methods=['POST'])
@basic_auth.required
def cotacao():
    try:
        dados = request.get_json()
        dados_input = [dados[col] for col in colunas]
        preco = modelo.predict([dados_input])
    except Exception as e:
        return str(e), 500
    return jsonify({'preco': preco[0]})

app.run(debug=True)