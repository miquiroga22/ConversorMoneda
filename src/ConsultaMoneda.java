import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConsultaMoneda {
    private List<String> historialConversiones;

    public ConsultaMoneda() {
        historialConversiones = new ArrayList<>();
    }

    public Moneda moneda(String monedaOrigen, String monedaDestino, double cantidad) {
        try {
            // Construir la URI para la consulta a la API
            URI uri = URI.create("https://v6.exchangerate-api.com/v6/xxx/pair/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad);

            // Crear un cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Construir la solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            // Enviar la solicitud y recibir la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Analizar la respuesta JSON utilizando Gson y extraer el valor del resultado de la conversión
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            double resultado = jsonObject.get("conversion_result").getAsDouble(); // O usa "conversion_rate" dependiendo de la estructura de la respuesta

            // Crear un objeto Moneda y establecer el resultado de la conversión
            Moneda moneda = new Moneda();
            moneda.setResultado(resultado);

            // Registrar la conversión en el historial
            registrarConversion(monedaOrigen, monedaDestino, cantidad);

            return moneda;
        } catch (Exception e) {
            // Manejar la excepción adecuadamente
            e.printStackTrace();
            return null;
        }
    }

    public boolean esMonedaValida(String moneda) {
        // Lista de monedas válidas
        String[] monedasValidas = {"USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY", "INR", "SGD", "NZD"};

        // Verificar si la moneda está en la lista de monedas válidas
        for (String monedaValida : monedasValidas) {
            if (monedaValida.equals(moneda)) {
                return true;
            }
        }

        return false; // La moneda no está en la lista de monedas válidas
    }

    public void registrarConversion(String monedaOrigen, String monedaDestino, double cantidad) {
        LocalDateTime timeStamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeStampFormat = timeStamp.format(formatter);
        String registro = timeStampFormat + ": Convertidos " + cantidad + " " + monedaOrigen + " a " + monedaDestino;
        historialConversiones.add(registro);
    }

    public void mostrarHistorialConversiones() {
        System.out.println("Historial de Conversiones:");
        for (String registro : historialConversiones) {
            System.out.println(registro);
        }
    }
}





