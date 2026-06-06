public class PayrollCalculator {

    public double compSSS(double salary) {

        if (salary < 3250)
            return 135;
        else if (salary < 3750)
            return 157.50;
        else if (salary < 4250)
            return 180;
        else if (salary < 24750)
            return 1102.50;
        else
            return 1125;
    }

    public double compPhil(double salary) {

        if (salary <= 10000)
            return 300;
        else if (salary < 60000)
            return salary * 0.03;
        else
            return 1800;
    }

    public double compTax(double salary) {

        double tax = 0;

        if (salary < 20833)
            tax = 0;
        else if (salary < 33333)
            tax = (salary - 20833) * 0.20;
        else if (salary < 66667)
            tax = ((salary - 33333) * 0.25) + 2500;
        else
            tax = ((salary - 66667) * 0.30) + 10833;

        return tax;
    }
}