package org.firstinspires.ftc.teamcode.team.Merlin2;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "RealTeleOp", group = "Merlin2")//This NEEDS to be changed tp the name of the code
//@Disabled //Uncomment this if it is not wanted on the phone
public class Merlin2RealTeleOp extends Merlin2TeleOpMethods{

    public void init(){
        super.init();
    }
    @Override
    public void init_loop(){
    }
    @Override
    public void start(){}
    @Override
    public void loop(){

        super.driveChoice(super.LiftHeight);
        super.collection();
        super.TargetEncoder = super.launchBall(TargetEncoder);
        super.LiftHeight = liftCapBallLift();
        if(!super.ButtonPressed){
            super.LiftHeight = super.lift();
        }
        super.print(LiftHeight, TargetEncoder);

    }
    @Override
    public void stop(){}


}
