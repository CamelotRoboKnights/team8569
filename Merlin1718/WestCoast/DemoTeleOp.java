/**
 * This is the teleOp for testing the robot as well as demoing our robot. It provides a few extra
 * fun controls to entertain the audience that are unnecessary during the actual competition. It
 * also prints a lot more values which helps with debugging.
 */

package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name = "Demo Tele", group = "Cardinal")
//@Disabled //Uncomment this if it is not wanted on the phone
public class DemoTeleOp extends OpMode {

    public WestCoastHardware robot = new WestCoastHardware();//The hardware map needs to be the hardware map of the robot we are using

    public void init() {robot.init(hardwareMap);}

    @Override
    public void init_loop() { }

    @Override
    public void start() {}//This runs when the start button is pressed


    @Override
    public void loop() {//This runs while opmode is active
        robot.westCoast.teleOp(gamepad1);
        robot.glyphCollector.teleOp(gamepad2);
        if(gamepad1.b){
            robot.jewelSorter.raise();
        }
        if(gamepad1.x) {
            robot.jewelSorter.lower();//up and down are flipped
        }
        telemetry.addData("version", 20);
        telemetry.addData("Blue?: ", robot.jewelSorter.isBlue());
        telemetry.addData("Red?: ", robot.jewelSorter.isRed());
        telemetry.addData("color", robot.jewelSorter.jewelColor());
        telemetry.addData("height", robot.glyphCollector.getCurrentMotorPosition());
        telemetry.addData("angle", robot.navx.getCurrentOrientation());
        telemetry.addData("leftMotor", robot.westCoast.getLeftCurrentMotorPosition());
        telemetry.addData("rightMotor,", robot.westCoast.getRightCurrentMotorPosition());
        telemetry.addData("left,", robot.westCoast.leftMotor.getCurrentPosition());
        telemetry.addData("right", robot.westCoast.rightMotor.getCurrentPosition());
        telemetry.addData("current blue: ", robot.jewelSorter.color.blue());
        telemetry.addData("current Red: ", robot.jewelSorter.color.red());
        telemetry.update();
    }

    @Override
    public void stop() {}
}