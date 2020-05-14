package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

// Remover a throws para a boa solução
public class Program {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int number = sc.nextInt();
            System.out.print("Check-in date (dd/MM/yyyy): ");
            Date checkIn = sdf.parse(sc.next());
            System.out.print("Check-out date (dd/MM/yyyy): ");
            Date checkOut = sdf.parse(sc.next());
            // instanciei esta reserva
            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
            // atualizar reserva
            // Implementar o update de datas
            System.out.println();
            System.out.println("Enter data to update the reservation:");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());
            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());
            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation updated: " + reservation);
        }
        catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        // Se for exceção padrão
        // catch (IllegalArgumentException e) {
        // Exceção personalizada
        catch (DomainException e) {
            System.out.println(e.getMessage()); // retorna a exceção tratada
        }
        catch (InputMismatchException e) {
            System.out.print("Input Mismatch");
        }
        // igual ao WHEN OTHERS THEN
        catch (RuntimeException e) {
            System.out.println("Unexpected error");
        }

        /* Para a GOOD SOLUTION,tirar todas as validações do programa e colocar nas classes
        // Verificar se a data de checkOut é menor que a data de checkIn
        // ! => Significa NÃO, ou seja, SE NÃO
        // se a data de checkout NÃO FOR maior que a de checkin...
        if (!checkOut.after(checkIn)) {
            System.out.println("Error in reservation: Check-out date must be after check-in date");
        }
        else {
            // instanciei esta reserva
            Reservation reservation = new Reservation(number,checkIn,checkOut);
            System.out.println("Reservation: " + reservation);

            // Implementar o update de datas
            System.out.println();
            System.out.println("Enter data to update the reservation:");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());
            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            /* Versão VERY BAD
            // fazendo de forma ruim!!
            // Pq lógica de programação no programa principal
            Date now = new Date();
            // || significa OU
            // se datas do checkin e checkout forem antes de hoje
            if (checkIn.before(now) || (checkOut.before(now))) {
                System.out.println("Error in reservation: Reservation dates for update must be future dates");
            }
            else if (!checkOut.after(checkIn)) {
                System.out.println("Error in reservation: Check-out date must be after check-in date");
            }
            else {
                // atualizar reserva
                reservation.updateDates(checkIn, checkOut);
                System.out.println("Reservation: " + reservation);
            }

            // Versão BAD
            // atualizar reserva recebendo String
            String error = reservation.updateDates(checkIn, checkOut);
            if (error != null) {
                System.out.println("Error in reservation: " + error);
            }
            else {
                System.out.println("Reservation: " + reservation);
            }
        }
        */
        sc.close();
    }
}
