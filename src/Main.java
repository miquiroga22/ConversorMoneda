import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsultaMoneda consultaMoneda = new ConsultaMoneda();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al convertidor de moneda!");

        boolean continuar = true;
        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Conversiones populares");
            System.out.println("2. Realizar una conversión personalizada");
            System.out.println("3. Mostrar historial de conversiones");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    realizarConversionesPopulares(consultaMoneda);
                    break;
                case 2:
                    realizarConversionPersonalizada(consultaMoneda, scanner);
                    break;
                case 3:
                    consultaMoneda.mostrarHistorialConversiones();
                    break;
                case 4:
                    continuar = false;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }

    private static void realizarConversionesPopulares(ConsultaMoneda consultaMoneda) {
        // Define las conversiones populares
        String[][] conversionesPopulares = {
                {"USD", "EUR"},
                {"USD", "GBP"},
                {"USD", "JPY"},
                {"USD", "CAD"},
                {"USD", "AUD"},
                {"USD", "CHF"},
                {"USD", "CNY"},
                {"USD", "INR"},
                {"USD", "SGD"},
                {"USD", "NZD"}
        };

        for (int i = 0; i < conversionesPopulares.length; i++) {
            String monedaOrigen = conversionesPopulares[i][0];
            String monedaDestino = conversionesPopulares[i][1];
            double cantidad = 1.0; // Puedes ajustar la cantidad según sea necesario
            realizarConversion(consultaMoneda, monedaOrigen, monedaDestino, cantidad);
        }
    }

    private static void realizarConversionPersonalizada(ConsultaMoneda consultaMoneda, Scanner scanner) {
        String monedaOrigen = solicitarMoneda("Ingrese la moneda de origen (por ejemplo, USD):", scanner);
        String monedaDestino = solicitarMoneda("Ingrese la moneda de destino (por ejemplo, EUR):", scanner);

        // Validar que las monedas tengan exactamente tres caracteres
        while (monedaOrigen.length() != 3 || monedaDestino.length() != 3) {
            System.out.println("Las monedas deben tener exactamente tres caracteres. Intente de nuevo.");
            monedaOrigen = solicitarMoneda("Ingrese la moneda de origen (por ejemplo, USD):", scanner);
            monedaDestino = solicitarMoneda("Ingrese la moneda de destino (por ejemplo, EUR):", scanner);
        }

        double cantidad = solicitarCantidad(scanner);

        // Verificar si las monedas ingresadas son válidas
        if (consultaMoneda.esMonedaValida(monedaOrigen) && consultaMoneda.esMonedaValida(monedaDestino)) {
            realizarConversion(consultaMoneda, monedaOrigen, monedaDestino, cantidad);
        } else {
            System.out.println("Una o ambas monedas ingresadas no son válidas. ¿Desea ver las conversiones populares?");
            System.out.print("Ingrese 'si' para ver las conversiones populares o 'no' para salir: ");
            String opcion = scanner.next().toLowerCase();
            if (opcion.equals("si")) {
                realizarConversionesPopulares(consultaMoneda);
            } else {
                System.out.println("Operación cancelada. ¡Hasta luego!");
            }
        }
    }


    private static String solicitarMoneda(String mensaje, Scanner scanner) {
        System.out.println(mensaje);
        return scanner.next().toUpperCase();
    }

    private static double solicitarCantidad(Scanner scanner) {
        double cantidad = 0.0;
        boolean cantidadValida = false;
        while (!cantidadValida) {
            System.out.println("Ingrese la cantidad a convertir:");
            String cantidadString = scanner.next();
            try {
                cantidad = Double.parseDouble(cantidadString);
                if (cantidad <= 0.0) {
                    System.out.println("La cantidad debe ser mayor que cero. Intente de nuevo.");
                } else {
                    cantidadValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número decimal válido. Intente de nuevo.");
            }
        }
        return cantidad;
    }

    private static void realizarConversion(ConsultaMoneda consultaMoneda, String monedaOrigen, String monedaDestino, double cantidad) {
        Moneda resultado = consultaMoneda.moneda(monedaOrigen, monedaDestino, cantidad);
        if (resultado != null) {
            consultaMoneda.registrarConversion(monedaOrigen, monedaDestino, cantidad); // Registra la conversión en el historial
            System.out.println("El resultado de la conversión es: " + cantidad + " " + monedaOrigen + " equivale a " + resultado.getResultado() + " " + monedaDestino);
        } else {
            System.out.println("No se pudo realizar la conversión. Por favor, asegúrate de ingresar monedas válidas.");
        }
    }

}

