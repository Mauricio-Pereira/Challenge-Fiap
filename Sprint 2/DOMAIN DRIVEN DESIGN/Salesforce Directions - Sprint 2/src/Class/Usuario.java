package Class;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

public class Usuario {

    private static final Pattern Name_Pattern = Pattern.compile("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
    private static final Pattern PhonePattern = Pattern.compile("^\\s*(\\d{2})[-. ]?(\\d{5}|\\d{4})[-. ]?(\\d{4})[-. ]?\\s*$");
    private static final Pattern EmailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern CepPattern = Pattern.compile("^\\d{5}-\\d{3}");
    private String nome;
    private String sobrenome;

    private String email;
    private String nascimento;
    private String genero;
    private String telefone;
    private String rua;
    private int numeroResidencia;
    private String complemento;
    private String cep;
    private String cidade;

    public Usuario(String nome, String sobrenome, String email, String nascimento, String genero, String telefone, String rua, int numeroResidencia, String complemento, String cep, String cidade) {
        if (Name_Pattern.matcher(nome).matches()) {
            this.nome = nome;
        }
        this.sobrenome = sobrenome;
        this.email = email;
        if (DATE_PATTERN.matcher(nascimento).matches() && isDateValid(nascimento)) {
            this.nascimento = nascimento;
        }
        String generoUpperCase = genero.toUpperCase();

        if ("MASCULINO".equals(generoUpperCase) || "FEMININO".equals(generoUpperCase) || "OUTRO".equals(generoUpperCase)) {
            this.genero = generoUpperCase;
        }
        if (Pattern.matches(String.valueOf(PhonePattern), telefone)) {
            this.telefone = telefone;
        }
        this.rua = rua;
        this.numeroResidencia = numeroResidencia;
        this.complemento = complemento;
        if (Pattern.matches(String.valueOf(CepPattern), cep)) {
            this.cep = cep;
        }
        this.cidade = cidade;

        if(EmailPattern.matcher(email).matches()) {
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
        if (Name_Pattern.matcher(nome).matches()) {
            this.nome = nome;
        }
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {if (Name_Pattern.matcher(sobrenome).matches()) {
        this.sobrenome = sobrenome;
    }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(EmailPattern.matcher(email).matches()) {
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

    public int getnumeroResidencia() {
        return numeroResidencia;
    }

    public void setnumeroResidencia(int numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
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
                "\r\nRua: " + rua +
                "\r\nNumero da Residência: " + numeroResidencia +
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


