package org.firstinspires.ftc.teamcode.team.Merlin1718;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


public class SpecificHardware {
    public static class JewelSorter {
        ColorSensor color;
        Servo servo;
        double up;
        double down;
        double redJewelThreshold = 0;
        double blueJewelThreshold = 0;

        JewelSorter (ColorSensor color, Servo servo, double up, double down){
            this.color = color;
            this.servo = servo;
            this.up = up;
            this.down = down;
        }
        public boolean lower () {
            this.servo.setPosition(this.down);
            return true;
        }
        public boolean raise () {
            this.servo.setPosition(this.up);
            return true;
        }
        public String jewelColor () {
            if (this.color.red() > redJewelThreshold) return "red";
            else if (this.color.blue() > blueJewelThreshold) return "blue";
            return "null";
        }
        public boolean isBlue () {
            return this.color.blue() > blueJewelThreshold;
        }
        public boolean isRed () {
            return this.color.red() > redJewelThreshold;
        }
    }
    public static class GlyphCollector {
        DcMotor motor;

        Servo leftGrasper;
        Servo rightGrasper;

        double leftGrasperOpen;
        double leftGrasperClosed;
        double rightGrasperOpen;
        double rightGrasperClosed;

        double motorEncoderValue = motor.getCurrentPosition();
        double maximumHeight;

        GlyphCollector (DcMotor motor, Servo leftGrasper, Servo rightGrasper, double leftGrasperOpen, double leftGrasperClosed, double rightGrasperOpen, double rightGrasperClosed, double maximumHeight){
            this.motor = motor;
            this.leftGrasper = leftGrasper;
            this.rightGrasper = rightGrasper;
            this.leftGrasperOpen = leftGrasperOpen;
            this.leftGrasperClosed = leftGrasperClosed;
            this.rightGrasperOpen = rightGrasperOpen;
            this.rightGrasperClosed = rightGrasperClosed;
            this.maximumHeight = maximumHeight;
        }
        public boolean open (){
            this.leftGrasper.setPosition(leftGrasperClosed);
            this.rightGrasper.setPosition(rightGrasperClosed);

            return true;
        }
        public boolean closed (){
            this.leftGrasper.setPosition(leftGrasperOpen);
            this.rightGrasper.setPosition(rightGrasperOpen);

            return true;
        }
//        public boolean raiseToValue (double targetPosition) {
//            double differnce = targetPosition - motorEncoderValue;
//            if (Math.abs())
//        }
        public void teleGlyph (double value) {
            if(motorEncoderValue >= -10 && motorEncoderValue <= maximumHeight && Math.abs(value) > .01)
                this.motor.setPower(value);
            else if (!(motorEncoderValue <= maximumHeight) && value > .01)
                 this.motor.setPower(value);
            else if (!(motorEncoderValue >= -10) && value < -.01)
                this.motor.setPower(value);
            else
                this.motor.setPower(0);

        }
    }
}
