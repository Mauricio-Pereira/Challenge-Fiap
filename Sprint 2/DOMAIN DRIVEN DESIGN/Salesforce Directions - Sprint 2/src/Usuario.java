import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.regex.Pattern;

public class Usuario {

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^\\d{2}/\\d{2}/\\d{4}$");
    private static Pattern PhonePattern = Pattern.compile("^\\s*(\\d{2})[-. ]?(\\d{5}|\\d{4})[-. ]?(\\d{4})[-. ]?\\s*$");

    private static Pattern CepPattern = Pattern.compile("^\\d{5}-\\d{3}");
    private String nome;
    private String sobrenome;

    private String email;
    private String nascimento;
    private String genero;
    private String telefone;
    private String rua;
    private int numeroRua;
    private String complemento;
    private String cep;
    private String cidade;

    public Usuario(String nome, String sobrenome, String email, String nascimento, String genero, String telefone, String rua, int numeroRua, String complemento, String cep, String cidade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.nascimento = nascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.rua = rua;
        this.numeroRua = numeroRua;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;

        if (this.email.contains("@") && this.email.contains(".com")) {
            this.email = email;
        } else {
            this.email = "";
        }
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if (this.email.contains("@") && this.email.contains(".com")) {
            this.email = email;
        } else {
            this.email = "";
        }

    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        if (DATE_PATTERN.matcher(nascimento).matches() && isDateValid(nascimento)) {
            this.nascimento = nascimento;
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        String generoUpperCase = genero.toUpperCase();

        if ("MASCULINO".equals(generoUpperCase) || "FEMININO".equals(generoUpperCase) || "OUTRO".equals(generoUpperCase)) {
            this.genero = generoUpperCase;
        }

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (Pattern.matches(String.valueOf(PhonePattern), telefone)) {
            this.telefone = telefone;
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroRua() {
        return numeroRua;
    }

    public void setNumeroRua(int numeroRua) {
        this.numeroRua = numeroRua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (Pattern.matches(String.valueOf(CepPattern), cep)) {
            this.cep = cep;
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "\r\nNome: " + nome +
                "\r\nSobrenome: " + sobrenome +
                "\r\nEmail: " + email +
                "\r\nNascimento: " + nascimento +
                "\r\nGênero: " + genero +
                "\r\nTelefone: " + telefone +
                "\r\nRua: " + rua + '\'' +
                "\r\nNumero da Residência: " + numeroRua +
                "\r\nComplemento: " + complemento +
                "\r\nCep: " + cep +
                "\r\nCidade: " + cidade
                ;
    }

    public static boolean isDateValid(String strDate) {
        String dateFormat = "dd/MM/uuuu";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isMaiorDeIdade() {
        if (nascimento == null) {
            return false;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(nascimento, formatter);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);

            return period.getYears() >= 18;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}


