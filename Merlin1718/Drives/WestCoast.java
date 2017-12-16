package org.firstinspires.ftc.teamcode.team.Merlin1718.Drives;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.util.Range;

public class WestCoast {
    public class WestCoastDrive {
        DcMotor leftMotor;
        DcMotor rightMotor;
        Wheel centerWheel = new Wheel(2);
        double ticksPerRotation = 1120;


        double leftMotorEncoder = this.leftMotor.getCurrentPosition();
        double rightMotorEncoder = this.rightMotor.getCurrentPosition();


        WestCoastDrive (DcMotor leftMotor, DcMotor rightMotor){
            this.leftMotor = leftMotor;
            this.rightMotor = rightMotor;
        }
        public void drive (double leftMotorPower, double rightMotorPower) {
            this.leftMotor.setPower(Range.clip(leftMotorPower, -1, 1));
            this.rightMotor.setPower(Range.clip(rightMotorPower, -1, 1));
        }

        boolean firstTime = true;
        double startEncoder = 0;
        public boolean driveBasedOnEncoders(double distance, int direction){

            boolean returnValue;
            double currentEncoder = (leftMotorEncoder + rightMotorEncoder)/ 2;
            double distanceTraveled = ((Math.abs(currentEncoder) / ticksPerRotation) * centerWheel.circumference);// need to subtract start encoder
            if(firstTime) {
                firstTime = false;
                startEncoder = currentEncoder;
            }
            if (distanceTraveled > distance) {//If I have gone the distance I want stop moving
                this.drive(0,0);
                firstTime = true;
                returnValue = true;
            }
            else {//Otherwise keep going
                returnValue = false;
                this.drive(.5*direction, .5*direction);
            }

            return returnValue;

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
                this.drive(0,0);
                returnValue = true;
            } else {//Otherwise it isn't done
                drive(-headingDiffernceScalled, headingDiffernceScalled);//My method to run the motors
                returnValue = false;
            }
            return returnValue;
        }


    }
    public class Wheel {
        double radius;
        double circumference =  2*Math.PI*radius;
        Wheel (double radius) {
            this.radius = radius;
        }
    }

}
