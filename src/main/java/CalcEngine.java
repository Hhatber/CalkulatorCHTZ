import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalcEngine {
    private static int time;
    private static final double timeWorkday = 480.0;
    private static final int one = 1;//Полученное время от пользователя
    private static double percentOfWorkday;

    //округление
    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    //Сколько процентов составляет time от числа timeWorkday ?
    private static void getPercentOfWorkday() {
        percentOfWorkday = 100.0 / (timeWorkday / (double) time);
        getPercentOfOne();
    }

    //Какое число соответствует percentOfWorkday % от числа 1 ?
    private static void getPercentOfOne() {
        //процент времени от рабочего дня в 8 часов (480 мин)
        double percentOfOne = (one / 100.0) * percentOfWorkday;
        percentOfOne = roundAvoid(percentOfOne, 5);
        String s = Double.toString(percentOfOne);
        System.out.println(s.replace('.', ',')); //с округлением + запятая вместо точки
        System.out.println();
    }

    //Ввод времени в минутах от пользователя
    private static void inputTime() {
        boolean work = true;
        while (work) {
            System.out.println("Введите время в минутах от 0 до 480");
            System.out.print("Ввод: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int x = 0;
            try {
                x = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Введено не корректное значение, повторите ввод");
                System.out.println();
                continue;
            }
            if (x >= 0 && x <= timeWorkday) {
                time = x;
                getPercentOfWorkday();
            } else {
                System.out.println("Введено число за пределами рабочего дня (0 - 480), повторите ввод");
            }
        }
    }

    public static void main(String[] args) {
        inputTime();
    }

}
