package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.opencv.core.Mat;


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


        GlyphGrasper leftGrasper;
        GlyphGrasper rightGrasper;

        double motorEncoderValue = motor.getCurrentPosition();
        double maximumHeight;
        double ticksPerRotation;
        double spoolDiameter;
        double spoolCircumference = spoolDiameter * Math.PI;

        GlyphCollector (DcMotor motor, GlyphGrasper leftGrasper, GlyphGrasper rightGrasper,
                        double maximumHeight, double ticksPerRotation, double spoolDiameter) {
            this.motor = motor;
            this.leftGrasper = leftGrasper;
            this.rightGrasper = rightGrasper;
            this.maximumHeight = maximumHeight;
            this.ticksPerRotation = ticksPerRotation;
            this.spoolDiameter = spoolDiameter;
        }
        private boolean open (){
            this.leftGrasper.open();
            this.rightGrasper.open();

            return true;
        }


        private boolean closed (){
            this.leftGrasper.close();
            this.rightGrasper.close();

            return true;
        }
        public boolean raiseToValue (double targetHeight) {//give a given height
            double difference = (targetHeight - motorEncoderValue/ticksPerRotation*spoolCircumference);//find the difference in inches
            double scalar = .5;//the scale value to determine motor speed
            this.motor.setPower(Range.clip(Math.abs(difference * scalar), -1, 1));
            if(Math.abs(difference) <= .1) {
                this.motor.setPower(0);
                return true;
            }
            return false;
        }


        boolean isOpen;
        boolean pressed = false;
        public void teleGlyph (double raiseValue, boolean openClose) {
            if(motorEncoderValue >= -10 && motorEncoderValue <= maximumHeight && Math.abs(raiseValue) > .01)
                this.motor.setPower(raiseValue);
            else if (!(motorEncoderValue <= maximumHeight) && raiseValue > .01)
                 this.motor.setPower(raiseValue);
            else if (!(motorEncoderValue >= -10) && raiseValue < -.01)
                this.motor.setPower(raiseValue);
            else
                this.motor.setPower(0);


            if(isOpen){
                this.open();
            } else {
                this.closed();
            }


            if(openClose && !pressed){
                pressed = true;
            } else if (!openClose && pressed){
                pressed = false;
                isOpen = !isOpen;
            }

        }
    }
    public static class GlyphGrasper {
        Servo servo;
        double openValue;
        double closeValue;
        GlyphGrasper (Servo servo, double openValue, double closeValue) {
            this.servo = servo;
            this.closeValue = closeValue;
            this.openValue = openValue;
        }
        public void open () {
            this.servo.setPosition(openValue);
        }
        public void close () {
            this.servo.setPosition(closeValue);
        }
    }
}
