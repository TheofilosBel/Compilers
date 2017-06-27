.intel_syntax noprefix # Use Intel syntax
.text

#### fun guts(ref s:char[]) : nothing ###
grc_puts :
	push ebp
	mov ebp, esp
    sub esp, 4      # we need 1 var to push to grc_putc

    mov  esi, DWORD PTR [ebp + 8]  # the address of the array
    mov  edi , 0                   # the offset of the array

    # Looop the array till we find the null terminate char
ploops:
	mov edx, DWORD PTR [esi + edi]  # load the arrays char in edx
    cmp edx, 0  # check if ut's the end of the string
    jne ploop
    jmp _grcputs
ploop:

	# Call grc_putc
	push edx
 	call grc_putc
	add esp, 4

	# - the offset
	sub edi, 4

	jmp ploops

_grcputs: mov esp, ebp
	pop ebp
	ret

#### fun gets(a:int; ref s:char[]) : nothing ###
grc_gets :
	push ebp
	mov ebp, esp
    sub esp, 4      # we need 1 var to push to grc

    mov  esi, DWORD PTR [ebp + 12] # the address of the array
    mov  ebx, DWORD PTR [ebp + 8]  # the size of the array
    mov  edi , 0                   # the offset of the array

    # Looop the array
gloops:  cmp ebx, 0
    jg gloop
    jmp _grcgets
gloop:
	# Call grc_getc
 	call grc_getc

 	# result stored in $eax
 	mov DWORD PTR [esi + edi], eax

	# - the size 
	sub ebx, 1

	# - the offset
	sub edi, 4

	jmp gloops

_grcgets: mov esp, ebp
	pop ebp
	ret

.data

#### fun strlen (ref s:char[]) : int ###
grc_strlen :
	push ebp
	mov ebp, esp
    sub esp, 4      # we need 1 var to push to grc_putc

    mov  esi, DWORD PTR [ebp + 12]  # the address of the array
    mov  edi , 0                   # the offset of the array
    mov  ebx , 0                   # keep the length of the string

    # Looop the array till we find the null terminate char
lenloops:
	mov edx, DWORD PTR [esi + edi]  # load the arrays char in edx
    cmp edx, 0                      # check if it's the end of the string
    jne lenloop
    jmp _grcstrlen
lenloop:

	# - the offset
	sub edi, 4

	# + the size 
	add ebx, 1

	jmp lenloops

_grcstrlen: mov eax, ebx  # Load the strlen to eax (convention)
	mov esp, ebp
	pop ebp
	ret


### fun strcmp (ref str1, str2 : char[]) : int; ###

grc_strcmp :
	push ebp
	mov ebp, esp
    sub esp, 4      # we need 1 var to push to grc_putc

    mov  esi, DWORD PTR [ebp + 12]  # the address of the str1
    mov  ebx, DWORD PTR [ebp + 16]  # the address of the str2
    mov  edi, 0                     # the offset of the array's
    mov  eax, 0                     # keep the result of the func (convention)

    # Looop the 2 string till we find a differce or till one or both end
cmploops:
	mov edx, DWORD PTR [esi + edi]  # load the str1 char in edx
	mov ecx, DWORD PTR [ebx + edi]  # load the str2 char in ecx
    cmp edx, 0                      # check if it's the end of the str1
    je endOfStr1                 
    cmp ecx, 0                      # check if it's the end of the str2
    je endOfStr2
    cmp edx, ecx                    # else check the string 
    jg  s1greater                   # str1 greater
    jl  s2greater                   # str2 greater

	sub edi, 4                      # - the offset
	mov  eax, 0                     # they are equal till this offset
    jmp cmploops                    # if eqaul jump to the loop start

s1greater:
	mov eax, 1                      # result 1 (str1 greater)
	jmp _grcstrcmp 

s2greater:
	mov eax, -1                      # result -1 (str2 greater)
	jmp _grcstrcmp

endOfStr1:
	cmp ecx, 0                      # check if it's the end of the str2 too
    je _grcstrcmp                   # if they both end in the same offset then return
    mov eax, -1                     # else str2 is freater than str1
    jmp _grcstrcmp                  # and return

endOfStr2:
    mov eax, 1                      # str1 is obviusly greater than str2 cause it didn't end
    jmp _grcstrcmp                  # so return 1

_grcstrcmp: 
	mov esp, ebp
	pop ebp
	ret







