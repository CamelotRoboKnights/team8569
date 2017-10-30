package org.firstinspires.ftc.teamcode.team.Merlin1718.Scrimmage;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


class ScrimmageMeathods extends OpMode {

    private ScrimmageHardware robot = new ScrimmageHardware();//The hardware map needs to be the hardware map of the robot we are using

    public void init(){
        robot.init(hardwareMap);
    }
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){}
    @Override
    public void stop(){}



    private void moveMotorsPower (double Motor1Power, double Motor2Power, double Motor3Power, double Motor4Power){
        robot.Motor1.setPower(Range.clip(Motor1Power, -1, 1));
        robot.Motor2.setPower(Range.clip(Motor2Power,-1,1));
        robot.Motor3.setPower(Range.clip(Motor3Power, -1, 1));
        robot.Motor4.setPower(Range.clip(Motor4Power, -1, 1));
    }

    public double[] joyValues(){
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

    public double[] makeFieldOriented(double[] originalXYZ, double OrientationDegrees) {

        double[] fieldOrientedXYZ = new double[3];

        double OrientationRadians = OrientationDegrees * Math.PI / 180;
        fieldOrientedXYZ[0] = -originalXYZ[1] * Math.sin(OrientationRadians) + originalXYZ[0] * Math.cos(OrientationRadians);
        fieldOrientedXYZ[1] = originalXYZ[1] * Math.cos(OrientationRadians) + originalXYZ[0] * Math.sin(OrientationRadians);
        fieldOrientedXYZ[2] = originalXYZ[2];

        return fieldOrientedXYZ;
    }

    public void Drive(double[] givenXYZ) {
        if (Math.abs(givenXYZ[1]) >= 0.01 || Math.abs(givenXYZ[0]) >= 0.01) {

            double Motor1Power = givenXYZ[1] - givenXYZ[0];
            double Motor2Power = givenXYZ[1] + givenXYZ[0];
            double Motor3Power = givenXYZ[1] - givenXYZ[0];
            double Motor4Power = givenXYZ[1] + givenXYZ[0];

            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);

        } else if (Math.abs(givenXYZ[2]) >= .01) {
            double Motor1Power = -givenXYZ[2] * .5;
            double Motor2Power = givenXYZ[2] * .5;
            double Motor3Power = givenXYZ[2] * .5;
            double Motor4Power = -givenXYZ[2] * .5;

            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);

        } else {//If none of these is true turn the power off to the motors to stop the robot
            moveMotorsPower(0, 0, 0, 0);
        }
    }
}

