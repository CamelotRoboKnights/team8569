package org.firstinspires.ftc.teamcode.team.Merlin1718;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

public class HolonomicClass {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;
    private double ticksPerRotation;
    private double radius;
    private double circumference = 2*Math.PI*radius;
    HolonomicClass(DcMotor frontLeftMotor, DcMotor frontRightMotor, DcMotor backRightMotor,
                   DcMotor backLeftMotor, double ticksPerRotation, double radius){
        this.frontLeftMotor = frontLeftMotor;
        this.frontRightMotor = frontRightMotor;
        this.backRightMotor = backRightMotor;
        this.backLeftMotor = backLeftMotor;
        this.ticksPerRotation = ticksPerRotation;
        this.radius = radius;
    }
    public void drive (double frontLeftPower, double frontRightPower, double backRightPower, double backLeftPower){
        //this method rangeclips the motorpower to be from 1 to -1
        frontLeftMotor.setPower(Range.clip(frontLeftPower, -1, 1));
        frontRightMotor.setPower(Range.clip(frontRightPower,-1,1));
        backRightMotor.setPower(Range.clip(backRightPower, -1, 1));
        backLeftMotor.setPower(Range.clip(backLeftPower, -1, 1));
    }
    private class JoyValues {
        double x;
        double y;
        double z;

        JoyValues (double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public JoyValues joyValues(Gamepad g){
        JoyValues joyXYZ = new JoyValues(0, 0, 0);
        //Set value x to the x axis on the left joystick if the value is above .01
        if(Math.abs(g.left_stick_x) > .01){
            joyXYZ.x = g.left_stick_x;
            //otherwise set vaue to 0
        }
        //Set value y to the y axis on the left joystick if the value is above .01
        if(Math.abs(g.left_stick_y) > .01){
            joyXYZ.y = -g.left_stick_y;
            //otherwise set value to 0
        }
        //Set value z to the z axis on the left trigger if the value is above .01
        if(g.left_trigger > .01){
            joyXYZ.z = -g.left_trigger;
            //Set value z to the z axis on the right trigger if the value is above .01
        } else if (g.right_trigger > .01){
            joyXYZ.z = g.right_trigger;
            //if the value is not above .01 set the value to 0
        }
        return joyXYZ;
    }
    public JoyValues makeFieldOriented(JoyValues originalXYZ, double orientationDegrees) {

        JoyValues fieldOrientedXYZ = new JoyValues(0, 0, 0);

        double orientationRadians = orientationDegrees * Math.PI / 180;
        fieldOrientedXYZ.x = -originalXYZ.y * Math.sin(orientationRadians) + originalXYZ.x * Math.cos(orientationRadians);
        fieldOrientedXYZ.y = originalXYZ.y * Math.cos(orientationRadians) + originalXYZ.x * Math.sin(orientationRadians);
        fieldOrientedXYZ.z = originalXYZ.z;

        return fieldOrientedXYZ;
    }
    public void teleDrive(JoyValues givenXYZ) {
        if (Math.abs(givenXYZ.x) >= 0.01 || Math.abs(givenXYZ.y) >= 0.01) {

            double frontLeftMotorPower = givenXYZ.y + givenXYZ.x;
            double frontRightMotorPower = givenXYZ.y - givenXYZ.x;
            double backRightMotorPower = givenXYZ.y + givenXYZ.x;
            double backLeftMotorPower = givenXYZ.y - givenXYZ.x;

            drive(frontLeftMotorPower, frontRightMotorPower, 
                    backRightMotorPower, backLeftMotorPower);
            // otherwise move motors accordingly
        } else if (Math.abs(givenXYZ.z) >= .01) {
            double frontLeftMotorPower = givenXYZ.z * .5;
            double frontRightMotorPower = -givenXYZ.z * .5;
            double backRightMotorPower = -givenXYZ.z * .5;
            double backLeftMotorPower = givenXYZ.z * .5;

            drive(frontLeftMotorPower, frontRightMotorPower, 
                    backRightMotorPower, backLeftMotorPower);

        } else {//If none of these is true turn the power off to the motors to stop the robot
            drive(0, 0, 0, 0);
        }
    }
    private void moveDirection (String direction, double power) {
        switch (direction){
            case "Forward":
                drive(power, power, power, power);
                break;
            case "Back":
                drive(-power, -power, -power, -power);
                break;
            case "Right":
                drive(power, -power, power, -power);
                break;
            case "Left":
                drive(-power, power, -power, power);
                break;
            default:
                drive(0,0,0,0);
                break;
        }
    }
    private double getCurrentTicks (DcMotor motor){
        if(!(motor == null)) {
            return motor.getCurrentPosition();
        }
        else return -8569;
    }
    boolean firstTime = true;
    double startEncoder = 0;
    private double currentEncoder (DcMotor motor) {
        double currentEncoder = 0;
        if(firstTime) {//If it is the first time running the code get the starting value
            startEncoder = this.getCurrentTicks(motor);
            firstTime = false;
        }
        currentEncoder = Math.abs(getCurrentTicks(motor) - startEncoder);
        return currentEncoder;
    }
    boolean turnToGyroHeading(double targetHeading, double currentHeading) {//Working and will turn the robot to a gyro heading within 2degrees
        boolean returnValue;//The value the method will return
        double headingDifference = targetHeading - currentHeading;//How far the robot is from its target heading
        double headingScaler = .005;//The scalier that edits how much the speed is affect
        double headingDiffernceScalled = headingDifference * headingScaler;//The scaled value that is used for the motor power
        headingDiffernceScalled = Range.clip(headingDiffernceScalled, -1, 1);//Making sure that the number is within a reasonable motor power

        if(headingDiffernceScalled < .09 && headingDiffernceScalled > 0){//making sure the motor power is not so low that the robot wont move
            headingDiffernceScalled = .09;
        }
        else if(Math.abs(headingDiffernceScalled) < .09 && headingDiffernceScalled < 0){//making sure the motor power is not so low that the robot wont move
            headingDiffernceScalled = -.09;
        }
        else{
        }
        if (1 >= Math.abs(headingDifference)) {//If it is within 2 degrees I am done
            drive(0,0,0,0);
            returnValue = true;
        } else {//Otherwise it isn't done
            drive(-headingDiffernceScalled, headingDiffernceScalled, headingDiffernceScalled, -headingDiffernceScalled);//My method to run the motors
            returnValue = false;
        }
        return returnValue;
    }
    boolean driveBasedOnEncoders(double distance, String direction){
        boolean returnValue;
        double CurrentEncoder = currentEncoder(frontLeftMotor);
        double distanceTraveled = ((Math.abs(CurrentEncoder) / ticksPerRotation) * circumference)*1.125;
        if (distanceTraveled > distance) {//If I have gone the distance I want stop moving
            drive(0,0,0,0);
            returnValue = true;
            firstTime = true;
        }
        else {//Otherwise keep going
            returnValue = false;
            moveDirection(direction, .5);
        }
        return returnValue;

    }
}
