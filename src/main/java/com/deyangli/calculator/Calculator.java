package com.deyangli.calculator;

import org.springframework.stereotype.Service;

/**
 * Calculator Service
 * @author Deyang Li
 *
 */
@Service
public class Calculator {
  int sum(int a, int b) {
    return a + b;
  }
}
