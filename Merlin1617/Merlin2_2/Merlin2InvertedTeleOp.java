//This is done being commented 17-1-13
/*
 * This is our new TeleOp and has the methods separate.
 */
package org.firstinspires.ftc.teamcode.team.Merlin1617.Merlin2_2;

import android.hardware.TriggerEventListener;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "InvertedOp", group = "Merlin2")//This NEEDS to be changed tp the name of the code
//@Disabled //Uncomment this if it is not wanted on the phone
public class Merlin2InvertedTeleOp extends Merlin2TeleOpMethods {
    private double StartTime;
    private String CurrentCase = "AllSet";

    public void init(){//This only runs once
        super.init();//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
        StartTime = System.currentTimeMillis();
    }
    @Override
    public void start(){}//This runs when the start button is pressed
    @Override
    public void loop(){//This runs while opmode is active

        double CurrentTime = (System.currentTimeMillis() - StartTime)/1000;
        XResetYaw();

        if(CurrentTime >= 60){
            CurrentCase = super.primeCapBallLift();
        }

        if(CurrentCase.equals("AllSet")) {
            super.driveChoiceInverted(super.LiftHeight);//The method that determines what drive program to use and gives the programs everything it needs
            super.collection();//Runs my collection method
            super.TargetEncoder = super.launchBall(super.TargetEncoder);//Run the launch ball method


            if(CurrentTime >= 90) {
                if(!super.LiftButtonPressed) {
                    super.LiftHeight = super.lowerCapBallLift();//Run the method that lifts the cap ball all the way when a button is pressed
                }
                else{
                    LiftHeight = liftCapBallLift();
                }
                if(!super.LowerButtonPressed){
                    super.LiftHeight = super.liftCapBallLift();
                }
                else{
                    super.LiftHeight = super.lowerCapBallLift();
                }
                if (!super.LiftButtonPressed && !super.LowerButtonPressed) {//If that button is not pressed
                    super.LiftHeight = super.lift();//Raise the cap ball lift manually
                }
            }
        }

        telemetry.addData("Lift", LiftButtonPressed);
        telemetry.addData("Lower", LowerButtonPressed);
        super.print(super.LiftHeight, super.TargetEncoder);//Print everything that I want

    }
    @Override
    public void stop(){}


}
