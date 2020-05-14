package model.exceptions;

// Porque DOMAIN, porque é uma exceção lançada pela minha entidade de dominio, neste caso Reservation
// Exception no sufixo

// Esta classe pode ser uma extensão da classe EXCEPTION ou da RUNTIMEEXCEPTION
// RuntimeException o compilador não obrigad vc a tratar
public class DomainException extends Exception {
    // Quano uma classe é serializable ela exige que seja informada a versão
    // Declaração de uma classe serializable
    private static final long serialVersionUID = 1L;

    // Construtor padrão
    public DomainException(String msg) {
        super(msg);
    }


}
