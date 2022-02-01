package config;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import static helpers.DateTimeFormatter.*;
import static helpers.SubcategoryGenerator.getRandomSubcategoryFromCategoryValue;

public class TestData {

    public static final String xTimeZone = "Europe/Moscow";
    public static final int timeZone = 3;

    public static class UserEmails {
        Faker generate = new Faker(new Locale("en-US"));
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        String[] userEmail = {
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c
        };

        public String[] userEmail() {
            return userEmail;
        }
    }

    public static class UserPasswords {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userPassword = {
                generate.internet().password(),
                generate.internet().password()
        };

        public String[] userPassword() {
            return userPassword;
        }
    }

    public static class UserFirstNames {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userFirstName = {
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName()
        };

        public String[] userFirstName() {
            return userFirstName;
        }
    }

    public static class UserLastNames {
        Faker generate = new Faker(new Locale("en-US"));
        String animalName = generate.animal().name();
        String[] userLastName = {
                generate.name().lastName() + generate.name().lastName(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix()
        };

        public String[] userLastName() {
            return userLastName;
        }
    }

    public static class UserPhoneNumbers {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userPhoneNumber = {
                "911" + generate.number().digits(7),
                "923" + generate.number().digits(7)
        };

        public String[] userPhoneNumber() {
            return userPhoneNumber;
        }
    }

    public static class UserCountries {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userCountry = {
                "Russia",
                "Russia"
        };

        public String[] userCountry() {
            return userCountry;
        }
    }

    public static class UserCities {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userCity = {
                "Moscow",
                "Moscow"
        };

        public String[] userCity() {
            return userCity;
        }
    }

    public static class ServiceNames {
        Faker generate = new Faker(new Locale("en-US"));
        String[] serviceName = {
                generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")",
                generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")"
        };

        public String[] serviceName() {
            return serviceName;
        }
    }

    public static class ServiceDescriptions {
        Faker generate = new Faker(new Locale("en-US"));
        String[] serviceDescription = {
                generate.lorem().characters(20, 2000),
                generate.lorem().characters(20, 2000)
        };

        public String[] serviceDescription() {
            return serviceDescription;
        }
    }

    public static class ServiceDurations {
        Faker generate = new Faker(new Locale("en-US"));
        String[] serviceDuration = {
                String.valueOf(15*(generate.number().numberBetween(1, 5))),
                String.valueOf(15*(generate.number().numberBetween(1, 5)))
        };

        public String[] serviceDuration() {
            return serviceDuration;
        }
    }

    public static class ServicePrices {
        Faker generate = new Faker(new Locale("en-US"));
        String[] servicePrice = {
                String.valueOf(generate.number().numberBetween(1, 9999999)),
                String.valueOf(generate.number().numberBetween(1, 2000))
        };

        public String[] servicePrice() {
            return servicePrice;
        }
    }

    public static class Specializations {
        Faker generate = new Faker(new Locale("en-US"));
        String[] specialization = {
                generate.job().title() + " " + generate.ancient().god(),
                generate.job().title() + " " + generate.ancient().hero()
        };

        public String[] specialization() {
            return specialization;
        }
    }

    public static String[] dateTimes() { //get 3 unique DateTimes for booking

        String dateTime1 = getDateTime();
        String date1 = dateTime1.substring(0, 10);

        String dateTime2 = getDateTime();
        String date2 = dateTime2.substring(0, 10);
        while (date1.equals(date2)) {
            dateTime2 = getDateTime();
            date2 = dateTime2.substring(0, 10);
        }

        String dateTime3 = getDateTime();
        String date3 = dateTime3.substring(0, 10);
        while (date3.equals(date1) || date3.equals(date2)) {
            dateTime3 = getDateTime();
            date3 = dateTime3.substring(0, 10);
        }

        return new String[]{dateTime1, dateTime2, dateTime3};
    }

    public static String
            service1Country,
            service1City,
            service1Address,
            service1Distance,
            service2Country,
            service2City,
            service2Address,
            service2Distance,
            reviewText1,
            reviewText2,
            masterComment,
            testMessage1,
            emptySpace,
            today,
            tomorrow,
            random,
            randomFile,
            man,
            male,
            woman,
            female,
            person,
            company,
            junior,
            middle,
            senior,
            onlineLocation,
            clientLocation,
            professionalLocation;

    public static Integer
            cad,
            eur,
            rub,
            usd,
            online,
            client,
            professional,
            randomServiceCategory,
            randomServiceSubcategory,
            randomRating,
            randomCurrency,
            unitsKilometers,
            unitsMiles;

    public static Object
            emptyNull;

    public static Boolean
            on,
            off,
            instantBooking,
            noInstantBooking,
            forAnotherPerson,
            forMyself;

    public static String[]
            paymentCashOnline = {"cash", "online"},
            paymentCash = {"cash"},
            paymentOnline = {"online"};

    public static void setTestData() {
        //currency
        cad = 0;
        eur = 1;
        rub = 2;
        usd = 3;

        //service location
        online = 0;
        client = 1;
        professional = 2;

    //service location strings
        onlineLocation = "Online";
        clientLocation = "Client's place";
        professionalLocation = "Professional's place";

        //gender helpers
        man = "man";
        male = "man";
        woman = "woman";
        female = "woman";

        //select person or company
        person = "person";
        company = "company";

        //select level of the professional
        junior = "junior";
        middle = "middle";
        senior = "senior";

        //units of distance
        unitsKilometers = 0;
        unitsMiles = 1;

        //random confirmation
        random = "random";

        on = true;
        off = false;
        instantBooking = true;
        noInstantBooking = false;
        forAnotherPerson = true;
        forMyself = false;

        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int year = currentDate.getYear();
        today = Integer.toString(day);
        tomorrow = getDayXDaysForward(1);

        Faker generate = new Faker(new Locale("en-US"));
        emptyNull = null;
        emptySpace = " ";
        randomServiceCategory = generate.number().numberBetween(0, 8);
        randomServiceSubcategory = getRandomSubcategoryFromCategoryValue(randomServiceCategory);
        randomFile = "src/test/resources/img/" + generate.number().numberBetween(1, 12) + ".png";
        randomRating = generate.number().numberBetween(1, 5);
        randomCurrency = generate.number().numberBetween(0, 3);

        service1Country = "Russia";
        service1City = "Moscow";
        service1Address = generate.address().fullAddress();
        service1Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service2Country = "Russia";
        service2City = "Moscow";
        service2Address = generate.address().fullAddress();
        service2Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        reviewText1 = generate.rickAndMorty().quote();
        reviewText2 = generate.chuckNorris().fact();
        masterComment = generate.backToTheFuture().quote();

        testMessage1 = generate.dragonBall().character() + " > " + generate.dragonBall().character();
    }

    public static String
            userCountry,
            userCity,
            userFirstName,
            userLastName,
            userEmailRandom,
            userEmailUppercase,
            userEmailLowercase,
            userEmailMixedCase,
            userPasswordRandom,
            userPhoneNumber,
            clientCountry,
            clientCity,
            clientFirstName,
            clientLastName,
            clientEmailRandom,
            clientPasswordRandom,
            clientPhoneNumber,
            serviceNameRandom,
            serviceDescriptionRandom,
            serviceDurationRandom,
            servicePriceRandom,
            servicePriceMin,
            servicePriceMax,
            serviceCurrencyRandom,
            serviceSpecializationRandom,
            serviceAddress,
            serviceDistance,
            bookingDateTimeRandom,
            masterEducationUniversity;

    public static int
            serviceLocationRandomDistance;

    public static void setRandomData() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');

        Faker generate = new Faker(new Locale("en-US"));
        userCountry = "Russia";
        userCity = "Moscow";
        userFirstName = generate.name().firstName();
        userLastName = generate.name().lastName();
        userEmailRandom = generate.lorem().characters(8, 12) + "@" + generate.lorem().word() + ".pp";
        userPasswordRandom = generate.internet().password();
        userPhoneNumber = "911" + generate.number().digits(7);

        clientCountry = "Russia";
        clientCity = "Moscow";
        clientFirstName = generate.name().firstName();
        clientLastName = generate.name().lastName();
        clientEmailRandom = generate.lorem().characters(8, 12) + "@" + generate.lorem().word() + ".cl";
        clientPasswordRandom = generate.internet().password();
        clientPhoneNumber = "911" + generate.number().digits(7);

        serviceNameRandom = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        serviceDescriptionRandom = generate.lorem().characters(20, 200);
        serviceDurationRandom = new String[]{"0", "15", "30", "45"}[(int) (Math.random() * 4)];
        servicePriceRandom = String.valueOf(generate.number().numberBetween(1, 500));
        servicePriceMin = String.valueOf(generate.number().numberBetween(1, 40000));
        servicePriceMax = String.valueOf(generate.number().numberBetween(1, 40000) + Integer.parseInt(servicePriceMin));
        serviceDurationRandom = new String[]{"15", "30", "45"}[(int) (Math.random() * 3)];
        serviceSpecializationRandom = generate.job().title() + " " + generate.ancient().god();
        serviceAddress = generate.address().fullAddress();
        serviceDistance = String.valueOf(generate.number().numberBetween(0, 9999));
        serviceLocationRandomDistance = generate.number().numberBetween(1, 9999);

        bookingDateTimeRandom = getDateTime();

        masterEducationUniversity = generate.university().name();

        userEmailMixedCase = (generate.lorem().characters(2, 4)).toUpperCase() + (generate.lorem().characters(2, 4)).toLowerCase() + (generate.lorem().characters(2, 4)).toUpperCase() + "@" + (generate.lorem().characters(1, 2)).toUpperCase() + (generate.lorem().characters(1, 2)).toLowerCase() + "." + Character.toUpperCase(c) + Character.toLowerCase(c);
        userEmailUppercase = userEmailMixedCase.toUpperCase();
        userEmailLowercase = userEmailMixedCase.toLowerCase();
    }
}