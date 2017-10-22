package org.firstinspires.ftc.teamcode.team.Merlin1718;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.toString;


class TeleOpMeathods1_d17_10_5 extends OpMode {

    //private Merlin2Hardware robot = new Merlin2Hardware();//The hardware map needs to be the hardware map of the robot we are using

    public void init(){
        //robot.init(hardwareMap);
    }
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){}
    @Override
    public void stop(){}


    private double servoTicksPerRotation;
    private double[] kpid = new double[] {1/180,1,3};

    private double[] joyValues(){
        double[] joyXYZ;
        joyXYZ = new double[3];
        if(Math.abs(gamepad1.left_stick_x) > .01){
            joyXYZ[0] = gamepad1.left_stick_x;
        }
        if(Math.abs(gamepad1.left_stick_y) > .01){
            joyXYZ[1] = -gamepad1.left_stick_y;
        }
        if(gamepad1.left_trigger > .01){
            joyXYZ[2] = -gamepad1.left_trigger;
        } else if (gamepad1.right_trigger > .01){
            joyXYZ[2] = gamepad1.right_trigger;
        }
        return joyXYZ;
    }
    private double[] makeFieldOriented(double[] originalXYZ, double OrientationDegrees) {

        double[] fieldOrientedXYZ = new double[3];

        double OrientationRadians = OrientationDegrees * Math.PI / 180;
        fieldOrientedXYZ[0] = -originalXYZ[1] * Math.sin(OrientationRadians) + originalXYZ[0] * Math.cos(OrientationRadians);
        fieldOrientedXYZ[1] = originalXYZ[1] * Math.cos(OrientationRadians) + originalXYZ[0] * Math.sin(OrientationRadians);
        fieldOrientedXYZ[2] = originalXYZ[2];

        return fieldOrientedXYZ;
    }
    private double[] turnToPolar(double[] XYZCoordinates){
        double[] newPolar = new double[3];
        newPolar[0] = Math.sqrt(Math.pow(XYZCoordinates[0], 2) + Math.pow(XYZCoordinates[1], 2));
        newPolar[1] = Math.acos(XYZCoordinates[0] / newPolar[0]);
        newPolar[2] = XYZCoordinates[2];
        return newPolar;
    }
    private double[] turnWheelModuleToPosition(int wheel, double targetAngle){
        double currentAngle = servoAngle(wheel);
        double power;
        power = pid(targetAngle, currentAngle);
        servoPower(wheel, power);
        return new double[] {power, currentAngle};
    }

    private long lastTime;
    private double errSum, lastErr, ITerm;
    private double pid (double target, double current) {
        long now = System.currentTimeMillis();
        double timeChange = (double)(now - lastTime);

        double error = target - current;
        errSum += (error * timeChange);
        double dErr = (error - lastErr) / timeChange;

        lastErr = error;
        lastTime = now;

        return kpid[0] * error + kpid[1] * errSum + kpid[2] * dErr;
    }




    private void servoPower (int servo, double power) {
        power = Range.clip(power, 0,1);
        switch (servo){
            case 1:
                //robot.servo1.setPosition(power)
                break;
            case 2:
                //robot.servo2.setPosition(power)
                break;
            case 3:
                //robot.servo3.setPosition(power)
                break;
            case 4:
                //robot.servo4.setPosition(power)
                break;
            default:
                telemetry.addData("WTF Why are you not using 1-4", servo);
                telemetry.update();
                break;
        }
    }
    private double servoAngle(int servo){
        switch (servo){
            case 1:
                // return currentTicks/servoTicksPerRotation
                break;
            case 2:
                // return currentTicks/servoTicksPerRotation
                break;
            case 3:
                // return currentTicks/servoTicksPerRotation
                break;
            case 4:
                // return currentTicks/servoTicksPerRotation
                break;
            default:
                telemetry.addData("WTF Why are you not using 1-4", servo);
                telemetry.update();
                return 0;
        }
        return 0;
    }

    /*   public void XResetYaw(){
            if(gamepad1.x){
                robot.navx_device.zeroYaw();
                }
        }
    */
    //turns compas values to degrees from start

}

