// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class ArmCmd extends Command {
  private final Arm arm;
  private final double targetAngle;

  public ArmCmd(Arm arm, double angle) {
    this.arm = arm;
    this.targetAngle = angle;
    
    addRequirements(arm);
  }

  @Override
  public void initialize() {
    arm.moveToAngle(targetAngle);
  }

  @Override
  public void execute() {
    arm.updatePID(); 
  }

  @Override
  public void end(boolean interrupted) {
    arm.stop();
  }

  @Override
  public boolean isFinished() {
    return Math.abs(targetAngle - arm.getCurrentAngle()) < 2.0;
  }
}
