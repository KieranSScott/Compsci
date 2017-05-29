; login name - ksco491 
; This program is my own unaided work, and was not copied,  
; nor written in collaboration with any other person. 
; Kieran Scott

; r0 - input character and starting message
; r1 - temp results
; r2 - input buffer
; r3 - store first buffer location
; r4 - holds value -30 to reduce value down to z or n
	.orig x3000
main	lea	r0, prompt
	lea	r2, input
	and	r3, r3, #0
	add	r3, r2, #0

	puts			;print the promt to screen

loop	getc			;then start using r0 to 
	putc			;hold user input
	str	r0, r2, #0	;storing value
	add	r2, r2, #1	;incrementing place in buffer	
	add	r1, r0, #-10	;checking if user pressed enter
	brz	test1		;newline enterd 
	brnzp	loop	

test1	add	r3, r3, #1	;find second address 
	ldr	r1, r3, #0	;load value at location from address stored in r3
	add	r1, r1, #-10	;check if it is a newline 
	brnp	finish		;if so, it is only one value entred
test2	add	r3, r3, #-1	;put address of first value back
	ldr	r1, r3, #0
	ld 	r4, ascii
	not	r4, r4
	add	r4, r4, #1	;turn value x30 to x-30
	add	r1, r1, r4	;turning ascii value into integer value
	add	r1, r1, #-9	;checking its between 0 and 9
	brnz	binary
finish	halt

;r1 - temp results
;r3 - buffer location which holds the digit inputed
;r4 - holds value of negative x30
;each part of binary checks what number has been entred
;then branchs to the fuction that will print the related 
;binary value
binary	
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-1
	brn	vzero
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-2
	brn	vone
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-3
	brn	vtwo
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-4
	brn	vthree
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-5
	brn	vfour
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-6
	brn	vfive
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-7
	brn	vsix
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-8
	brn	vseven
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-9
	brn	veight
	ldr	r1, r3, #0
	add	r1, r1, r4
	add	r1, r1, #-10
	brn	vnine
	brzp	finish

;each one loads the string of the related bianry value
;then prints and returns to the main function
vzero	lea	r0, zero
	puts
	lea	r0, newline
	puts	
	brnzp	main
vone	lea	r0, one
	puts
	lea	r0, newline
	puts	
	brnzp	main
vtwo	lea	r0, two
	puts
	lea	r0, newline
	puts	
	brnzp	main
vthree	lea	r0, three
	puts
	lea	r0, newline
	puts	
	brnzp	main
vfour	lea	r0, four
	puts
	lea	r0, newline
	puts	
	brnzp	main
vfive	lea	r0, five
	puts
	lea	r0, newline
	puts	
	brnzp	main
vsix	lea	r0, six
	puts
	lea	r0, newline
	puts	
	brnzp	main
vseven	lea	r0, seven
	puts
	lea	r0, newline
	puts
	brnzp	main
veight	lea	r0, eight
	puts
	lea	r0, newline
	puts	
	brnzp	main
vnine	lea	r0, nine
	puts
	lea	r0, newline
	puts	
	brnzp	main
	

;binary table	 
zero	.stringz "0000"
one	.stringz "0001"
two	.stringz "0010"
three	.stringz "0011"
four	.stringz "0100"
five	.stringz "0101"
six	.stringz "0110"	
seven	.stringz "0111"
eight	.stringz "1000"
nine	.stringz "1001"

prompt	.stringz "Enter a digit: "
input	.blkw	10
ascii	.fill	x30
newline	.fill	xA
	.end