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
        double ticksPerRotation = 560;

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

        public void drive (double leftMotorPower, double rightMotorPower) {
            this.leftMotor.setPower(Range.clip(leftMotorPower, -1, 1));
            this.rightMotor.setPower(Range.clip(rightMotorPower, -1, 1));
        }

        public void arcadeJoystick (double stick_y, double stick_x, double speed) {
            double leftMotorPower = stick_y + stick_x;
            double rightMotorPower = stick_y - stick_x;
            this.drive(leftMotorPower * speed, rightMotorPower * speed);
        }

        public void teleOp (Gamepad g) {
            if(Math.abs(g.left_stick_y) > .01 || Math.abs(g.left_stick_x) > .01) this.arcadeJoystick(-g.left_stick_y, g.left_stick_x, 1);
            else if (Math.abs(g.right_stick_y) > .01 || Math.abs(g.right_stick_x) > .01) this.arcadeJoystick(-g.left_stick_y, g.right_stick_x, .5);
            else drive(0,0);
        }
            // this makes the encoders 0 so our measurments dont get messed up


        boolean firstTime = true;
        double startEncoder = 0;
        public boolean driveBasedOnEncoders(double distance, int direction){

            boolean returnValue;
            double currentEncoder = getLeftCurrentMotorPosition();
            if(firstTime) {
                firstTime = false;
                startEncoder = currentEncoder;
            }
            double distanceTraveled = ((Math.abs(currentEncoder  - startEncoder)));
            if (distanceTraveled > distance) {//If I have gone the distance I want stop moving
                this.drive(0,0);
                firstTime = true;
                returnValue = true;
            }
            else {//Otherwise keep going
                returnValue = false;
                this.drive(.1*direction, .1*direction);
            }

            return returnValue;

        }

        boolean turnToGyroHeading(double targetHeading, double currentHeading) {//Working and will turn the robot to a gyro heading within 2degrees
            boolean returnValue;//The value the method will return
            double headingDifference = targetHeading - currentHeading;//How far the robot is from its target heading
            double headingScaler = .003;//The scalier that edits how much the speed is affect
            double headingDiffernceScalled = headingDifference * headingScaler;//The scaled value that is used for the motor power
            headingDiffernceScalled = Range.clip(headingDiffernceScalled, -1, 1);//Making sure that the number is within a reasonable motor power

            if(headingDiffernceScalled < .01 && headingDiffernceScalled > 0){//making sure the motor power is not so low that the robot wont move
                headingDiffernceScalled = .02;
            }
            else if(Math.abs(headingDiffernceScalled) < .01 && headingDiffernceScalled < 0){//making sure the motor power is not so low that the robot wont move
                headingDiffernceScalled = -.02;
            }

            if (.5 >= Math.abs(headingDifference)) {//If it is within 2 degrees I am done
                this.drive(0,0);
                returnValue = true;
            } else {//Otherwise it isn't done
                drive(headingDiffernceScalled, -headingDiffernceScalled);//My method to run the motors
                returnValue = false;
            }
            return returnValue;
        }

    }

}
