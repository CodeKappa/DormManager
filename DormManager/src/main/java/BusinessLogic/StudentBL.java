package BusinessLogic;

import DataAccess.StudentDA;
import Model.ProgramSpalatorie;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class StudentBL
{
    private StudentDA studentDA;
    private StudentDA adminDA;

    public StudentBL()
    {
        studentDA = new StudentDA();
        adminDA = new StudentDA();
    }

    public void addProgramare(LocalDate currentDate, int zi, int ora, int camera, int spalatorie)
    {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        currentDate = currentDate.plusDays(zi - dayOfWeek.getValue());
        String data = currentDate.toString();
        if(ora < 10) data +=  " 0" + ora + ":00:00";
        else data +=  " " + ora + ":00:00";
        studentDA.addProgramare(new ProgramSpalatorie(null, spalatorie, camera, data));
    }

    public void removeProgramare(LocalDate currentDate, int zi, int ora, int camera, int spalatorie)
    {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        currentDate = currentDate.plusDays(zi - dayOfWeek.getValue());
        String data = currentDate.toString();
        if(ora < 10) data +=  " 0" + ora + ":00:00";
        else data +=  " " + ora + ":00:00";
        studentDA.removeProgramare(spalatorie, camera, data);
    }

    public int getRoom(String email)
    {
        return studentDA.getRoom(email);
    }

    public int getSpalatorie(String email)
    {
        int camera = studentDA.getRoomName(email);
        int etaj = camera / 100;
        String camin = studentDA.getCaminStudent(studentDA.getCNP(email));
        ArrayList<ArrayList<String>> spalatorii = studentDA.getSpalatorii(camin);
        for (ArrayList<String> spalatorie : spalatorii)
        {
            if (Integer.parseInt(spalatorie.get(1)) / 100 == etaj)
            {
                return Integer.parseInt(spalatorie.get(0));
            }
        }
        return 0;
    }

    public ArrayList<ArrayList<String>> getProgramari(LocalDate currentDate, int spalatorie, int camera, String email)
    {
        int numeCamera = studentDA.getRoomName(email);
        ArrayList<ArrayList<String>> programari = studentDA.getProgramari(spalatorie);

        ArrayList<ArrayList<String>> programariSaptamanaCurenta = new ArrayList<>();

        for (ArrayList<String> programare : programari)
        {
            int idCamera = Integer.parseInt(programare.get(0));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(programare.get(1), formatter);
            DayOfWeek dayOfWeek = dateTime.getDayOfWeek();

/*            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int week1 = currentDate.get(weekFields.weekOfWeekBasedYear());
            int week2 = dateTime.get(weekFields.weekOfWeekBasedYear());*/
            LocalDateTime date = currentDate.atStartOfDay();
            LocalDateTime startOfWeek1 = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDateTime startOfWeek2 = dateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

            WeekFields weekFields = WeekFields.of(Locale.getDefault());

            int week1 = startOfWeek1.get(weekFields.weekOfWeekBasedYear());
            int week2 = startOfWeek2.get(weekFields.weekOfWeekBasedYear());

            if (week1 == week2)
            {
                ArrayList<String> programareSaptamanaCurenta = new ArrayList<>();

                programareSaptamanaCurenta.add(String.valueOf(dateTime.getHour()));
                programareSaptamanaCurenta.add(String.valueOf(dayOfWeek.getValue()-1));
                programareSaptamanaCurenta.add(String.valueOf(studentDA.getRoomName(idCamera)));

                programariSaptamanaCurenta.add(programareSaptamanaCurenta);
            }
        }
        return programariSaptamanaCurenta;
    }


}
