package helpers;

import org.junit.jupiter.api.Test;

import static java.lang.String.valueOf;

public final class ServiceDuration {
    public String days;
    public String hours;
    public String minutes;

    public ServiceDuration(String days, String hours, String minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    public static ServiceDuration getDuration(String value) {
        int durationTotalInt = Integer.parseInt(value);
        if (durationTotalInt < 0) {
            System.out.println("Wrong service duration value, can not be below 0, value: " + value);
            throw new IllegalArgumentException();
        } else {
            int
                    daysInt = durationTotalInt / 24 / 60,
                    hoursInt = durationTotalInt / 60 % 24,
                    minutesInt = durationTotalInt % 60;
            String
                    days = valueOf(daysInt),
                    hours = valueOf(hoursInt),
                    minutes = valueOf(minutesInt);

            System.out.println("days: " + days + "\nhours: " + hours + "\nminutes: " + minutes);
            return new ServiceDuration(days, hours, minutes);
        }
    }
}

