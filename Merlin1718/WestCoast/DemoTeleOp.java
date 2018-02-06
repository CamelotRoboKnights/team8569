/**
 * This is the teleOp for testing the robot as well as demoing our robot. It provides a few extra
 * fun controls to entertain the audience that are unnecessary during the actual competition. It
 * also prints a lot more values which helps with debugging.
 */

package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

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
        robot.relic.teleOp(gamepad2);
        if(gamepad1.b){
            robot.jewelSorter.raise();
        }
        if(gamepad1.x) {
            robot.jewelSorter.lower();//up and down are flipped
        }

//        if(gamepad2.left_trigger > .01) {
//            robot.relic.motor.setPower(gamepad2.left_trigger/2);
//        } else if (gamepad2.right_trigger > .01) {
//            robot.relic.motor.setPower(-gamepad2.right_trigger);
//        } else {robot.relic.motor.setPower(0);}

        telemetry.addData("version", 20);
        telemetry.addData("Blue?: ", robot.jewelSorter.isBlue());
        telemetry.addData("Red?: ", robot.jewelSorter.isRed());
        telemetry.addData("color", robot.jewelSorter.jewelColor());
        telemetry.addData("height", robot.glyphCollector.getCurrentMotorPosition());
        telemetry.addData("target height", robot.glyphCollector.raiseingToValue);
        //telemetry.addData("angle", robot.navx.getCurrentOrientation());
        telemetry.addData("leftMotor", robot.westCoast.getLeftCurrentMotorPosition());
        telemetry.addData("rightMotor,", robot.westCoast.getRightCurrentMotorPosition());
        telemetry.addData("left,", robot.westCoast.leftMotor.getCurrentPosition());
        telemetry.addData("right", robot.westCoast.rightMotor.getCurrentPosition());
        telemetry.addData("current blue: ", robot.jewelSorter.color.blue());
        telemetry.addData("current Red: ", robot.jewelSorter.color.red());
        telemetry.addData("rev first angle: ", robot.revIMU.getCurrentOrientation());
        telemetry.addData("rev second angle: ", robot.revIMU.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).secondAngle);
        telemetry.addData("rev third angle: ", robot.revIMU.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).thirdAngle);
        telemetry.addData("relic position", robot.relic.getPosition());
        telemetry.addData("relic target", robot.relic.currentTargetPosition);
        telemetry.addData("gamepad", gamepad2.right_stick_y);
        telemetry.update();
    }

    @Override
    public void stop() {}
}