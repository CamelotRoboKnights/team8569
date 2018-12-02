/**
 * This is the code for the object of the West Coast Drive
 * This object contains 2 motors and two wheels
 * When initializing this object it requires the two motors
 * It is set up to run with Andimark NeverRest 20's and wheels with a radius of 2
 * A wheel contains a radius property and a circumference property
 * When creating a wheel object one most give the radius which calculates circumference
 *
 * Generic
 *
 * It has a basic drive method which limits the values assigned to the motors to between -1 and 1
 * It has two methods (one for each motor) which give the current distance traveled
 *
 *
 * TeleOp
 *
 * It has a arcade joystick method (running both motors off one joystick) which can modulate power
 * It has a teleOp method that drives the robot with either the left joystick (full speed) or the
 *      right joystick (slow speed)
 *
 *
 * Auto
 *
 * It has a drive based on encoders method that drives a distance based on the encoders
 * It has a turn to gyro heading that takes a current heading and target heading and tries to turn
 *      the robot to face that heading
 */


package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

public class WestCoastClass {
        public static class WestCoastDrive {
                DcMotor leftMotor;
                DcMotor rightMotor;
                double radius = 2;
                double circumference = 2*Math.PI*radius;
                double ticksPerRotation = 1220;

        WestCoastDrive (DcMotor leftMotor, DcMotor rightMotor){
            this.leftMotor = leftMotor;
            this.rightMotor = rightMotor;
        }
        public double getLeftCurrentMotorPosition () {
            if (!(this.leftMotor == null)) {
                return this.leftMotor.getCurrentPosition()/this.ticksPerRotation*this.circumference;
            }
            else return -8569;
        }
        public double getRightCurrentMotorPosition () {
            if (!(this.rightMotor == null)) {
                return this.rightMotor.getCurrentPosition()/this.ticksPerRotation*this.circumference;
            }
            else return -8569;
        }
        public void drive (double leftMotorPower, double rightMotorPower, boolean isFront) {
            if(isFront) {
                this.leftMotor.setPower(Range.clip(leftMotorPower, -1, 1));
                this.rightMotor.setPower(Range.clip(rightMotorPower, -1, 1));
            } else {
                this.leftMotor.setPower(Range.clip(rightMotorPower, -1, 1));
                this.rightMotor.setPower(Range.clip(leftMotorPower, -1, 1));
            }
        }

        public void arcadeJoystick (double stick_y, double stick_x, double speed, boolean isFront) {
            if(isFront) {
                double leftMotorPower = Range.clip(stick_y + stick_x, -1, 1);
                double rightMotorPower = Range.clip(stick_y - stick_x, -1, 1);
                this.drive(leftMotorPower * speed, rightMotorPower * speed, true);
            } else {
                double leftMotorPower = Range.clip(-stick_y + stick_x, -1, 1);
                double rightMotorPower = Range.clip(-stick_y - stick_x, -1, 1);
                this.drive(leftMotorPower * speed, rightMotorPower * speed, true);
            }
        }
        public void teleOp (Gamepad g, boolean direction) {
            if(Math.abs(g.left_stick_y) > .01 || Math.abs(g.left_stick_x) > .01) this.arcadeJoystick(Math.pow(-g.left_stick_y,3), Math.pow(g.left_stick_x, 3)/2, 1, direction);
            else if (Math.abs(g.right_stick_y) > .01 || Math.abs(g.right_stick_x) > .01) this.arcadeJoystick(Math.pow(-g.right_stick_y, 3), Math.pow(g.right_stick_x, 3)/2, .5, direction);
            else drive(0,0, true);
        }


        boolean firstTime = true;
        double startEncoder = 0;
        public boolean driveBasedOnEncodersAndGyro(double distance, int direction, boolean isFront, double targetOrientation, double currentOrientation){

            boolean returnValue;
            double currentEncoder = getLeftCurrentMotorPosition();
            if(firstTime) {
                firstTime = false;
                startEncoder = currentEncoder;
            }
            double distanceTraveled = ((Math.abs(currentEncoder  - startEncoder)));
            if (distanceTraveled > distance) {//If I have gone the distance I want stop moving
                this.drive(0,0, isFront);
                firstTime = true;
                returnValue = true;
            }
            else {//Otherwise keep going
                returnValue = false;
                this.gyroStraightDrive(.45, direction, isFront, targetOrientation, currentOrientation);
            }

            return returnValue;

        }
        public boolean driveBasedOnEncoders(double distance, int direction, boolean isFront){

            boolean returnValue;
            double currentEncoder = getLeftCurrentMotorPosition();
            if(firstTime) {
                firstTime = false;
                startEncoder = currentEncoder;
            }
            double distanceTraveled = ((Math.abs(currentEncoder  - startEncoder)));
            if (distanceTraveled > distance) {//If I have gone the distance I want stop moving
                this.drive(0,0, isFront);
                firstTime = true;
                returnValue = true;
            }
            else {//Otherwise keep going
                returnValue = false;
                this.drive(.4*direction, .4*direction, isFront);
            }

            return returnValue;

        }

        boolean turnToGyroHeading(boolean isFront, double targetHeading, double currentHeading) {//Working and will turn the robot to a gyro heading within 2degrees
            boolean returnValue;//The value the method will return
            double headingDifference = targetHeading - currentHeading;//How far the robot is from its target heading
            if (headingDifference > 180){
                headingDifference = headingDifference - 360;
            } else if (headingDifference < -180) {
                headingDifference = headingDifference + 360;
            }
            double headingScaler = .02;//The scalier that edits how much the speed is affect
            double headingDiffernceScalled = headingDifference * headingScaler;//The scaled value that is used for the motor power
            headingDiffernceScalled = Range.clip(headingDiffernceScalled, -1, 1);//Making sure that the number is within a reasonable motor power

            if(headingDiffernceScalled < .15 && headingDiffernceScalled > 0){//making sure the motor power is not so low that the robot wont move
                headingDiffernceScalled = .15;
            }
            else if(Math.abs(headingDiffernceScalled) < .15 && headingDiffernceScalled < 0){//making sure the motor power is not so low that the robot wont move
                headingDiffernceScalled = -.15;
            }

            if (1 >= Math.abs(headingDifference)) {//If it is within 2 degrees I am done
                this.drive(0,0, isFront);
                returnValue = true;
            } else {//Otherwise it isn't done
                drive(headingDiffernceScalled, -headingDiffernceScalled, isFront);//My method to run the motors
                returnValue = false;
            }
            return returnValue;
        }

        void gyroStraightDrive(double speed, int direction, boolean isFront, double targetOrientation, double currentOrientation){
            double headingDifference = targetOrientation-currentOrientation;
            double scaler = .01;
            double leftMotorPower;
            double rightMotorPower;
            if(direction>0){
                leftMotorPower = speed+headingDifference*scaler*direction;
                rightMotorPower = speed-headingDifference*scaler*direction;
            } else {
                leftMotorPower = (speed-headingDifference*scaler)*direction;
                rightMotorPower = (speed+headingDifference*scaler)*direction;
            }
            this.drive(leftMotorPower, rightMotorPower, isFront);

        }
        public boolean betterDriveBasedOnEncodersAndGyro(double distance, boolean isFront, double targetOrientation, double currentOrientation){

            boolean returnValue;
            double currentEncoder = (getLeftCurrentMotorPosition() + getRightCurrentMotorPosition())/2;
            if(firstTime) {
                firstTime = false;
                startEncoder = currentEncoder;
            }
            double distanceTraveled = currentEncoder - startEncoder;
            double scalar = .1;
            double speed = Range.clip(distanceTraveled*scalar, -1, 1);
            int direction;
            if (distanceTraveled>0) direction = 1;
            else direction = -1;
            if (Math.abs(distanceTraveled - distance) > .5) {//If I have gone the distance I want stop moving
                this.drive(0,0, isFront);
                firstTime = true;
                returnValue = true;
            }
            else {//Otherwise keep going
                returnValue = false;
                this.gyroStraightDrive(speed, direction, isFront, targetOrientation, currentOrientation);
            }

            return returnValue;

        }

    }


}
