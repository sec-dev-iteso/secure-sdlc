# Buffer Overflow Exercises

## Introduction
These exercises are designed to provide hands-on experience with buffer overflow vulnerabilities. They cover basic and advanced scenarios, emphasizing the importance of understanding the system architecture and secure programming practices.

---

## Exercise 1: Simple Buffer Overflow and Analysis

### Objective
Understand and exploit a basic stack-based buffer overflow in a C program.

### System Architecture Retrieval
Run the following commands to determine the processor's architecture:
```
uname -a
lscpu
```
Search for architecture, CPU family and Model name.

### Steps
1. **Compile the Program:**
   - Compile the vulnerable C program with GCC:
     ```
     gcc -o buffer_overflow buffer_overflow.c -fno-stack-protector -z execstack -g
     ```
     *Note: Adjust the compilation flags based on the output of `uname` and `lscpu` if necessary.*

1. **Analyzing with objdump:**
   - Generate a disassembly of the program:
     ```
     objdump -d buffer_overflow > dump.txt
     ```
   - Read the assembler output. See [intel_assembly_guide.md](intel_assembly_guide.md)

1. **Running the Program:**
   - Test the program with an input that causes a buffer overflow:
     ```
     ./buffer_overflow $(python -c 'print "A"*60')
     ```

1. **Debugging:**
   - Use GDB for debugging:
     ```
     gdb ./buffer_overflow
     ```
     Follow [gdb_debugging.md](gdb_debugging.md)

1. **Fixing the Vulnerability:**
   - Modify the program to use `strncpy` instead of `strcpy`.
   - Consider the size of the buffer to pass the size of copied bytes.
   - Consider the EOF character.

1. **Recompile and Test the Fixed Program:**
   - ```
     gcc -o buffer_overflow_fixed buffer_overflow.c -g
     ```

---

## Exercise 2: Advanced Buffer Overflow and Defense

### Objective
Analyze a more complex buffer overflow and learn about defenses against buffer overflows.

### Steps
1. **Compile the Program:**
   - Compile the complex overflow program:
     ```
     gcc -o complex_overflow complex_overflow.c -m32 -fno-stack-protector -no-pie -D_FORTIFY_SOURCE=0 -g
     ```
     Note: This command compiles the program for a 32-bit architecture (making it easier to understand and exploit for educational purposes), disables stack protection, disables position-independent code, and turns off fortified source functions.
1. **Using objdump:**
   - Disassemble the program:
     ```
     objdump -M intel -d complex_overflow > dump.txt
     ```

1. **Running the Program:**
   - Run the program with an overflowing input:
     ```
     ./complex_overflow $(python -c 'print "A"*200')
     ```

1. **Debugging with GDB:**
   - Use GDB to analyze the program's flow:
     ```
     gdb ./complex_overflow
     ```

1. **Fixing the Vulnerability:**
   - Modify the program to use `snprintf` instead of `sprintf`.
   - Consider the size of the buffer to pass the size of copied bytes.
   - Consider the EOF character.

1. **Recompile and Test the Fixed Program:**
   - ```
     gcc -o complex_overflow_fixed complex_overflow.c -m32
     ```

1. **Bonus points**
    - Attemp to run the `secret_function` by passing an specific input that triggers this execution.

---

## Conclusion
These exercises are designed to provide practical experience with identifying, exploiting, and mitigating buffer overflow vulnerabilities. Understanding these concepts is critical for cybersecurity and secure programming practices.

