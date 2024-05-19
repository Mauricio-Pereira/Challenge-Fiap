package org.fiap.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Cliente extends _BaseEntity{


    private static final Pattern PhonePattern = Pattern.compile("^\\s*(\\d{2})[-. ]?(\\d{5}|\\d{4})[-. ]?(\\d{4})[-. ]?\\s*$");
    private static final Pattern EmailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern PasswordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private Endereco endereco;
    private String usuario;
    private String senha;

    public Cliente() {
    }

    public Cliente(int id, String nome, String sobrenome, LocalDate dataNascimento, String telefone, String email, String usuario, String senha) {
        super(id);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Cliente(int id, String nome, String sobrenome, String dataNascimento, String telefone, String email, Endereco endereco, String usuario, String senha) {
        super(id);

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.usuario = usuario;
        try {
            setDataNascimento(dataNascimento);
            setTelefone(telefone);
            setEmail(email);
            setSenha(senha);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        try {
            if (dataNascimento == null || !isDateValid(dataNascimento)) {
                throw new IllegalArgumentException("Data de nascimento inválida");
            } else {
                this.dataNascimento = LocalDate.parse(dataNascimento, dataFormat);
                if (!isMaiorDeIdade()) {
                    this.dataNascimento = null;
                    throw new IllegalArgumentException("Cliente menor de idade");
                }
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (!PhonePattern.matcher(telefone).matches()) {
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!EmailPattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (!PasswordPattern.matcher(senha).matches()) {
            throw new IllegalArgumentException("Senha inválida");
        }
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Cliente {\n" +
                "\tNome: '" + nome + "',\n" +
                "\tSobrenome: '" + sobrenome + "',\n" +
                "\tData de Nascimento: " + dataNascimento + ",\n" +
                "\tTelefone: '" + telefone + "',\n" +
                "\tEmail: '" + email + "',\n" +
                "\tEndereço: " + endereco+ ",\n" +
                "\tUsuário: '" + usuario + "',\n" +
                "\tSenha: '" + senha + "',\n" +
                "\t" + super.toString() + "\n" +
                "}";
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
        LocalDate now = LocalDate.now();
        Period period = Period.between(dataNascimento, now);
        return period.getYears() >= 18;
    }

    public Map<Boolean, String> validate(){
        Map<Boolean, String> validation = new HashMap<>();
        if (this.nome == null || this.nome.isEmpty())
            validation.put(false, "O nome do cliente não pode ser vazio");
        if (this.sobrenome == null || this.sobrenome.isEmpty())
            validation.put(false, "O sobrenome do cliente não pode ser vazio");
        if (this.dataNascimento == null)
            validation.put(false, "A data de nascimento do cliente não pode ser vazia");
        if (this.telefone == null || this.telefone.isEmpty())
            validation.put(false, "O telefone do cliente não pode ser vazio");
        if (this.email == null || this.email.isEmpty())
            validation.put(false, "O email do cliente não pode ser vazio");
        if (this.usuario == null || this.usuario.isEmpty())
            validation.put(false, "O usuário do cliente não pode ser vazio");
        if (this.senha == null || this.senha.isEmpty())
            validation.put(false, "A senha do cliente não pode ser vazia");
        return validation;
    }

}
