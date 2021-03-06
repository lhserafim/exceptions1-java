package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;
    // static p/ instanciar apenas uma vez
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException{ // propagar com throws
        if (!checkOut.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public long duration() {
        // Para achar a diferença entre os dias vou calcular usando milisegundos. Padrão JAVA
        long diff = checkOut.getTime() - checkIn.getTime(); // getTime() retorna em milisegundos
        // converter p/ dias
        return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
    }

    /* VERSÃO VERY BAD SOLUTION
    public void updateDates(Date checkIn, Date checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    */
    // VERSÃO BAD SOLUTIONS
    // Alterei o método p/ retornar String
    /*
    public String updateDates(Date checkIn, Date checkOut) {

        Date now = new Date();
        if (checkIn.before(now) || (checkOut.before(now))) {
            return "Reservation dates for update must be future dates";
        }
        if (!checkOut.after(checkIn)) {
            return "Check-out date must be after check-in date";
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        // para poder compilar, retorno NULL, pois a variável String exige.
        // neste caso, null = sucesso
        return null;
    }
    */
    // VERSÃO GOOD SOLUTION
    // Necessário propagar a exceção na declaração do método
    public void updateDates(Date checkIn, Date checkOut) throws DomainException {
        Date now = new Date();
        if (checkIn.before(now) || (checkOut.before(now))) {
            // usar throw para lançar a exceção e instanciar uma exceção de argumentos informados inválidos
            // usando exceções padrões
            //throw new IllegalArgumentException("Reservation dates for update must be future dates");

            // usando exceção personalizada
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!checkOut.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                + ", "
                + duration()
                + " nights.";
    }

}
