# Intel Assembly Language Quick Guide

## Introduction
Intel assembly language is a low-level programming language for Intel-compatible processors. It has two main variants: 32-bit and 64-bit. The 32-bit variant is known as IA-32, and the 64-bit variant is known as x86-64.

## General Concepts
- **Registers:** Small storage locations within the CPU used to hold data and addresses.
- **Instructions:** Commands that tell the processor what to do.
- **Syntax:** Intel syntax uses `operation destination, source` format.

## Key Registers
### IA-32 (32-bit)
- **General Purpose Registers:**
  - `EAX`, `EBX`, `ECX`, `EDX`: Used for arithmetic, data manipulation.
  - `ESI`, `EDI`: Used for string and memory array operations.
  - `EBP`: Base pointer for stack frames.
  - `ESP`: Stack pointer for top of the stack.
- **Segment Registers:** `CS`, `DS`, `ES`, `SS`, `FS`, `GS`
- **EIP:** Instruction pointer.

### x86-64 (64-bit)
- Extensions of 32-bit registers with a `R` prefix (e.g., `RAX`, `RBX`, `RCX`, `RDX`).
- Additional registers: `R8` to `R15`.
- **RSP:** Stack pointer.
- **RBP:** Base pointer.
- **RIP:** Instruction pointer.

## Common Instructions
- **Data Movement:** `MOV`, `PUSH`, `POP`
- **Arithmetic:** `ADD`, `SUB`, `MUL`, `DIV`
- **Control Flow:** `JMP`, `CALL`, `RET`, `JZ`, `JNZ`
- **String Operations:** `MOVS`, `LODS`, `STOS`
- **Function Prologue and Epilogue:**
  - Prologue: Prepare the stack and registers for function execution.
  - Epilogue: Clean up the stack and restore registers.

## Sample Code Snippet
### IA-32 Example
```asm
section .data
hello db 'Hello, world!', 0

section .text
global _start

_start:
    mov edx, 13           ; Message length
    mov ecx, hello        ; Message to write
    mov ebx, 1            ; File descriptor (stdout)
    mov eax, 4            ; System call number (sys_write)
    int 0x80              ; Call kernel

    mov eax, 1            ; System call number (sys_exit)
    int 0x80              ; Call kernel
```

### x86-64 Example
```asm
section .data
hello db 'Hello, world!', 0

section .text
global _start

_start:
    mov rdx, 13           ; Message length
    mov rsi, hello        ; Message to write
    mov rdi, 1            ; File descriptor (stdout)
    mov rax, 1            ; System call number (sys_write)
    syscall               ; Call kernel

    mov rax, 60           ; System call number (sys_exit)
    syscall               ; Call kernel
```
