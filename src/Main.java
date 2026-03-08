import java.util.Scanner;

public class Main {
    // ______________ SIMULADOR DE ALCANCIA _________________________________________________________________________
    // Autores: Juan Camilo Padilla Bulla y EL SEÑOR Fecha: 8/3/26
    // ______________ DECLARACION DE VARIABLES ______________________________________________________________________

    // Variables generales entre las funciones
    static byte escogeMenu;     // Es la opcion que escoge el usuario en el menu principal, usado en el switch
    static double dineroTotal;  // Es la totalidad del dinero almacenado en la alcancia
    static String decision;     // Caracter ingresado por el usuario para decidir si tomar una desicion o no

    // Variables unicas de la funcion anadirMonedas()
    static int valorIngresadoMoneda; // Es el valor de la moneda que ingresa el usuario a la alcancia en cada intento
    static boolean error_DenominacionInvalida; // Detecta si el usuario ingreso una denominacion de moneda invalida, para imprimir un mensaje u otro

    // Variables unicas de la funcion metaFinanciera()
    static boolean yaPregunto_laMeta = false; // Usada saber cuando preguntar al usuario si quiere cambiar la meta de ahorro que antes puso o no
    static int metaAhorro;      // Es el valor numerico de la meta de ahorro, que el usuario se propuso
    static double progresoMeta; // Es el porcentaje del progreso, que actualmente tiene el usuario en su meta

    // Variables que expresan el valor de cada moneda
    static int m20 = 20, m50 = 50, m100 = 100;
    static int m200 = 200, m500 = 500, m1000 = 1000;

    // Variables de que expresan la CANTIDAD de monedas de cada denominacion
    static int c20 = 0, c50 = 0, c100 = 0;
    static int c200 = 0, c500 = 0, c1000 = 0;


    static Scanner teclado = new Scanner (System.in);


    public static void main(String[] args) {

        // ______________ INICIO ALGORITMO _________________________________________________________________________
        do{
            System.out.println("""
           \nBienvenido a su alcancia personal, porfavor escoja una opcion:
                Ingrese 1, para AÑADIR MONEDAS A LA ALCANCIA
                Ingrese 2, para IMPRIMIR EL TOTAL DE DINERO ACTUAL Y SU EQUIVALENTE EN CADA DENOMINACION DE MONEDAS
                Ingrese 3, para MOSTRAR EL PROGRESO DE LA META DE AHORRO ACTUAL
                Ingrese 0, para SALIR""");

            escogeMenu = teclado.nextByte();

            //Calculamos el dineroTotal cada vez que estamos en el menu principal
            dineroTotal = (c20 * m20) + (c50 * m50) + (c100 * m100) + (c200 * m200) + (c500 * m500) + (c1000 * m1000);

            switch (escogeMenu){
                case 0:
                    //Procede a cerrar el ciclo, saliendo
                    break;
                case 1:
                    //Procede a ingresar monedas a la alcancia
                    anadirMonedas();
                    break;
                case 2:
                    //Imprime la cantidad y valor acumulado de monedas por denominacion, ademas del total de dinero en la alcancia
                    imprimir_EstadoMonedas();
                    break;
                case 3:
                    //Muestra el progreso de la meta actual
                    metaFinanciera();
                    break;
                default:
                    //Error, el usuario ingresa un valor que no esta
                    System.out.println("Error, porfavor ingrese un valor que coincida con las opciones");
                    break;
            }
        } while (escogeMenu != 0);
    }


    public static void anadirMonedas(){
        do{
            error_DenominacionInvalida = false; // se reinicia el valor del error para volver a evaluarlo mas tarde

            System.out.println("\nSolo puede añadir monedas de las siguientes denominaciones:\n     - 20    - 50   - 100\n     - 200   - 500    - 1000");
            System.out.println("     Porfavor ingrese el valor numerico de la moneda que añadira a su alcancia");
            valorIngresadoMoneda = teclado.nextInt();

            // Procede a aumentar la cantidad de monedas de la denominacion ingresada
            if (valorIngresadoMoneda == m20) {
                c20 = c20 + 1;
            }
            else if (valorIngresadoMoneda == m50) {
                c50 = c50 + 1;
            }
            else if (valorIngresadoMoneda == m100) {
                c100 = c100 + 1;
            }
            else if (valorIngresadoMoneda == m200) {
                c200 = c200 + 1;
            }
            else if (valorIngresadoMoneda == m500) {
                c500 = c500 + 1;
            }
            else if (valorIngresadoMoneda == m1000) {
                c1000 = c1000 + 1;
            } else {
                System.out.println("""
                Error, porfavor ingrese una moneda de entre las posibles denominaciones que se pueden ingresar, no otras.
                \nSi desea voverlo a intentar, ingrese > + <, para salir, cualquier otro caracter.""");
                error_DenominacionInvalida = true;
            }

            if (error_DenominacionInvalida == false) {
                System.out.println("\nSi desea añadir más monedas, ingrese > + <, para salir, cualquier otro caracter.");
            }

            decision = teclado.next();

        } while(decision.equalsIgnoreCase("+"));
    }


    public static void imprimir_EstadoMonedas(){

        System.out.println("\nEl estado actual de la alcancia es de:");
        System.out.println("    Hay $" + (c20 * m20) + " pesos en " + c20 + " moneda/s de $20");
        System.out.println("    Hay $" + (c50 * m50) + " pesos en " + c50 + " moneda/s de $50");
        System.out.println("    Hay $" + (c100 * m100) + " pesos en " + c100 + " moneda/s de $100");
        System.out.println("    Hay $" + (c200 * m200) + " pesos en " + c200 + " moneda/s de $200");
        System.out.println("    Hay $" + (c500 * m500) + " pesos en " + c500 + " moneda/s de $500");
        System.out.println("    Hay $" + (c1000 * m1000) + " pesos en " + c1000 + " moneda/s de $1000");

        System.out.println("\n  En total, usted posee: $" + (dineroTotal) + " pesos.");
    }


    public static void metaFinanciera(){
        do {
            if (yaPregunto_laMeta == false) {
                System.out.println("\nCual es tu meta de ahorro?, porfavor ingresa un valor entero expresado en pesos");
                metaAhorro = teclado.nextInt();
                yaPregunto_laMeta = true;
            } else {
                System.out.println("Desea cambiar su meta de ahorro de $" + metaAhorro + " pesos?, si es asi, ingrese > + <; de lo contraio, cualquier otra tecla");
                decision = teclado.next();
                if (decision.equalsIgnoreCase("+")) {
                    System.out.println("Cual es tu meta de ahorro?, porfavor ingresa un valor entero expresado en pesos");
                    metaAhorro = teclado.nextInt();
                }
            }

            if (metaAhorro <= 0) {
                System.out.println("Error, la meta de ahorro no puede ser menor o igual a cero, vuelvalo a intentar");
                // Usamos "yaPregunto_laMeta" para que no le aperesca el valor de "metaAhorro" al usuario, hasta que ingrese un nuevo valor
                yaPregunto_laMeta = false;
            } else {
                //Hacemos una regla de tres para calcular el porcentaje
                progresoMeta = ((dineroTotal * 100) / metaAhorro);
                System.out.println("Usted cuenta con $" + dineroTotal + " pesos en total.");
                System.out.println("Cuando su meta de ahorro es de $" + metaAhorro + " pesos.\nEl progreso de su meta se encuentra en el: " + progresoMeta + "%");
            }
        } while (metaAhorro <= 0);
    }
}