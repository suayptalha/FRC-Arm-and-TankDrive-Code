// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ArmCmd;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class RobotContainer {


  private final DriveTrain driveTrain = new DriveTrain();
  private final Joystick joy1 = new Joystick(Constants.JOY_PORT);
  private final Arm arm = new Arm();


  public RobotContainer() {
    driveTrain.setDefaultCommand(new TankDrive(driveTrain, () -> joy1.getRawAxis(0), () -> joy1.getRawAxis(1)));
    configureBindings();
  }

  private void configureBindings() {
  }

  public void handleJoystickInput() {
    if (joy1.getRawButtonPressed(1)) {
      CommandScheduler.getInstance().schedule(new ArmCmd(arm, 45)); // 1. tuşa basılınca 45 derecelik pozisyon
    } 
    else if (joy1.getRawButtonPressed(2)) {
      CommandScheduler.getInstance().schedule(new ArmCmd(arm, 90)); // 2 tuşa basılınca 90 derecelik pozisyon
    }
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
