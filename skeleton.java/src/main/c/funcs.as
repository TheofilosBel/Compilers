.intel_syntax noprefix # Use Intel syntax
.text

grc_puts :
	push ebp
	mov ebp, esp
    sub esp, 4      # we need 1 var to push to grc

    mov  eax, DWORD PTR [ebp + 12]  # the address of the array
    mov  ebx, DWORD PTR [ebp + 8] # the size of the array
    mov  ecx , 0                   # the offset of the array

    # Looop the array
loopS:  cmp ebx, 0
    jge loop
    jmp _grcputs
loop:
	# Call grc_putc
	mov edx, DWORD PTR [eax + ecx]
	push edx
 	call grc_putc
	add esp, 4

	# - the size 
	sub ebx, 1

	# + the offset
	add ecx, 4

	jmp loopS

_grcputs:
	mov esp, ebp
    pop ebp
    ret 