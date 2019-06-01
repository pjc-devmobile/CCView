package com.navan.customcalendarview.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//Observação: O Java considera janeiro como o mês 0. É preciso levar isto em consideração ao trabalhar com datas
public class DateUtils {

    private static final Locale locale = new Locale("pt", "BR");
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy", locale);

    // Formata um objeto Date no formato dd/MM/yyyy
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }

        return df.format(date);
    }

    //20 de jan de 2020
    public static String formatDateExtenso(Date date) {
        if (date == null) {
            return null;
        }

        String data = df.format(date);
        int mes = Integer.parseInt(data.substring(3, 5));

        if (mes == 1) {
            data = df.format(date).substring(0, 2) + " de janeiro";
        } else if (mes == 2) {
            data = df.format(date).substring(0, 2) + " de fevereiro";
        } else if (mes == 3) {
            data = df.format(date).substring(0, 2) + " de março";
        } else if (mes == 4) {
            data = df.format(date).substring(0, 2) + " de abril";
        } else if (mes == 5) {
            data = df.format(date).substring(0, 2) + " de maio";
        } else if (mes == 6) {
            data = df.format(date).substring(0, 2) + " de junho";
        } else if (mes == 7) {
            data = df.format(date).substring(0, 2) + " de julho";
        } else if (mes == 8) {
            data = df.format(date).substring(0, 2) + " de agosto";
        } else if (mes == 9) {
            data = df.format(date).substring(0, 2) + " de setembro";
        } else if (mes == 10) {
            data = df.format(date).substring(0, 2) + " de outubro";
        } else if (mes == 11) {
            data = df.format(date).substring(0, 2) + " de novembro";
        } else {
            data = df.format(date).substring(0, 2) + " de dezembro";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(new Date());

        if (calendar.get(Calendar.YEAR) == calendarToday.get(Calendar.YEAR))
            return data;
        else
            return data + " de " + calendar.get(Calendar.YEAR);

    }

    public static String formatDateExtensoMesAno(Date date) {
        if (date == null) {
            return null;
        }

        String data = df.format(date);
        int mes = Integer.parseInt(data.substring(3, 5));

        if (mes == 1) {
            data = "Janeiro";
        } else if (mes == 2) {
            data = "Fevereiro";
        } else if (mes == 3) {
            data = "Março";
        } else if (mes == 4) {
            data = "Abril";
        } else if (mes == 5) {
            data = "Maio";
        } else if (mes == 6) {
            data = "Junho";
        } else if (mes == 7) {
            data = "Julho";
        } else if (mes == 8) {
            data = "Agosto";
        } else if (mes == 9) {
            data = "Setembro";
        } else if (mes == 10) {
            data = "Outubro";
        } else if (mes == 11) {
            data = "Novembro";
        } else {
            data = "Dezembro";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(new Date());

        return data + " de " + calendar.get(Calendar.YEAR);

    }

    // Cria um objeto Date a partir de uma string no formato dd/MM/yyyy
    public static Date createDate(String date) {
        int[] info = parseDateInfo(date);
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.DAY_OF_MONTH, info[0]);
        c.set(Calendar.MONTH, info[1] - 1);
        c.set(Calendar.YEAR, info[2]);
        return c.getTime();
    }

    // Formata um string no formato dd/MM/yyyy a partir do ano, mês e dia fornecidos
    public static String formatDate(int year, int monthOfYear, int dayOfMonth) {
        return String.format(locale, "%02d/%02d/%04d", dayOfMonth, monthOfYear, year);
    }

    // Retorna a data de hoje em forma de um array
    // Posição 0: dia
    // Posição 1: mês
    // Posição 2: ano
    public static int[] today() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);

        return new int[]{day, month, year};
    }

    /**
     * Retorna um data qualquer, no formato dd/MM/yyyy, em forma de um array
     * Posição 0: dia
     * Posição 1: mês
     * Posição 2: ano
     */
    public static int[] parseDateInfo(String date) {
        try {
            String[] tokens = date.split("/");
            int day = Integer.parseInt(tokens[0]);
            int month = Integer.parseInt(tokens[1]);
            int year = Integer.parseInt(tokens[2]);

            return new int[]{day, month, year};

        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Retorna um intervalo de datas que compreende o mês e ano fornecidos. O retorno é em forma de um array
    // Posição 0: data inicial (em milisegundos)
    // Posição 1: data final (em milisegundos)
    public static long[] getRange(int mes, int ano) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(ano, mes - 1, 1);
        long dateFrom = c.getTimeInMillis();

        c.set(ano, mes - 1, 1, 23, 59, 59);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        long dateTo = c.getTimeInMillis();

        return new long[]{dateFrom, dateTo};
    }

    public static String getHoraFromDate(Long date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String formatDataHora(Date date) {
        String dataStr = "";
        if (date == null) {
            return dataStr;
        }

        dataStr = getHoraFromDate(date.getTime()) + ", " + df.format(date);
        return dataStr;
    }

    public static String[] getHoraAgora() {
        try {
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH");
            SimpleDateFormat sdfMinuto = new SimpleDateFormat("mm");

            String sHora, sMinuto;

            int hora = Integer.parseInt(sdfHora.format(System.currentTimeMillis()));
            int minuto = Integer.parseInt(sdfMinuto.format(System.currentTimeMillis()));

            if (hora < 10)
                sHora = "0" + hora;
            else
                sHora = String.valueOf(hora);

            if (minuto < 10)
                sMinuto = "0" + minuto;
            else
                sMinuto = String.valueOf(minuto);

            return new String[]{sHora, sMinuto};
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
