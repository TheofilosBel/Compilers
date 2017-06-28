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
gloops:  cmp ebx, 1                # If the size reaches one that means that we have one more char for \0
    jg gloop
    jmp getsnullTerminate
gloop:
	# Call grc_getc
 	call grc_getc               # result of grc_puts is stored in $eax

 	cmp eax, 10                 # compare the read char from getc with the ascii of \n
 	je  getsnullTerminate       # end reading when we get the \n char

 	# Else store the char to the string
 	mov DWORD PTR [esi + edi], eax

	# - the size 
	sub ebx, 1

	# - the offset
	sub edi, 4

	jmp gloops

getsnullTerminate :
	mov DWORD PTR [esi + edi], 0  # null terminate the strin 

_grcgets:
    mov esp, ebp
	pop ebp
	ret

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


### fun strcmp (ref str1, str2 : char[]) : int ###

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


### fun strcpy (ref trg, src : char[]) : nothing ###

grc_strcpy :
	push ebp
	mov ebp, esp
    sub esp, 4      # we need 1 var to push to grc_putc

    mov  esi, DWORD PTR [ebp + 8]  # the address of the trg
    mov  ebx, DWORD PTR [ebp + 12]  # the address of the src
    mov  edi, 0                     # the offset of the array's

    # Looop the src string and store it's chars to trg (no worries about trg's size :P)
cpyloops:
	mov ecx, DWORD PTR [ebx + edi]  # load the src char in ecx
    cmp ecx, 0                      # check if it's the end of the src
	je cpynullTerminate
	mov DWORD PTR [esi + edi], ecx  # assing the char of src to trg
	sub edi, 4                      # - the offset
    jmp cpyloops

cpynullTerminate:
    mov DWORD PTR [esi + edi], 0    # assing the null terminate char to trg
    jmp _grcstrcpy                  # and return

_grcstrcpy: 
	mov esp, ebp
	pop ebp
	ret

### fun strcat (ref trg, src : char[]) : nothing ###

grc_strcat :
	push ebp
	mov ebp, esp
    sub esp, 4      # we need 1 var to push to grc_putc

    mov  esi, DWORD PTR [ebp + 8]   # the address of the trg
    mov  ebx, DWORD PTR [ebp + 12]  # the address of the src
    mov  edi, 0                     # the offset of the src str
    mov  eax, 0                     # the offset of the trg str

    # First loop the trg to find the null terminated char 
cat1loops:
	mov ecx, DWORD PTR [esi + edi]  # load the src char in ecx
    cmp ecx, 0                      # check if it's the end of the src
	je cat2loops
	sub edi, 4                      # - the offset
    jmp cat1loops

    # Looop the src string and store it's chars to trg (no worries about trg's size :P)
cat2loops:
	mov ecx, DWORD PTR [ebx + eax]  # load the src char in ecx
    cmp ecx, 0                      # check if it's the end of the src
	je catnullTerminate
	mov DWORD PTR [esi + edi], ecx  # assing the char of src to trg
	sub eax, 4                      # - the offset of src
	sub edi, 4                      # - the offset of trg
    jmp cat2loops

catnullTerminate:
    mov DWORD PTR [esi + edi], 0    # assing the null terminate char to trg
    jmp _grcstrcat                  # and return

_grcstrcat: 
	mov esp, ebp
	pop ebp
	ret
