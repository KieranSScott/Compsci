; login name - ksco491 
; This program is my own unaided work, and was not copied,  
; nor written in collaboration with any other person. 
; Kieran Scott

;It is stated at the part of the program but I will also put
;that the two parts: multi and print are altered versions of
;code from the lecture slides, slides stated before each part.
;I have also recommented them and gone through to insure that 
;I understand what the code does.

;r0 - output
;r1 - ascii op temp value
;r2 - storing ascii value x30
;r3 - counter
;r4 - temp number
;r5 - buffer

		.orig x3000
main		lea	r0, prompt
		lea	r5, buffer
		and	r3, r3, #0
		puts
	
input		getc
		putc
		add	r4, r0, #-10
		brz	test1			;after done with input test for 2 digit input
		
		str	r0, r5, #0		;storing value
		add	r5, r5, #1		;incrementing place in buffer
		add	r3, r3, #1		;counting number of digits enterd
		brnzp 	input

;tests if value is an integer
test1		add	r4, r3, -2		;test if more than two values stored
		brp	main
		not	r4, r3			;negative value of position counter
		add	r4, r4, #1
		add	r5, r5, r4		;moving to position of first input value
		ldr	r1, r5, #0
		ld	r2, ascii		;offset of x30 to see if input is
		not	r2, r2			;digit or letter
		add	r2, r2, #1
		add	r1, r1, r2
		add	r1, r1, #-9		;taking x39 away and if negative or zero
		brnz	test2			;then go to next test
		brp	main

;checks weather integer is one or two digit
test2		add	r4, r3, #-1		;reducing counter by one
		brp	multi			;two digits found
		brn	main			;means no value was entred so prompts again

;turns ascii value into integer for single digit integer
single		ldr	r3, r5, #0		
		ld	r2, ascii
		not	r2, r2
		add	r2, r2, #1		;make ascii offset negative
		add	r3, r3, r2		;offset the single digit value

;tests if single value is under 3	
		and	r4, r4, #0
		add	r4, r3, #0
		brn	main			;negative value so prompt again
		add	r4, r4, #-3
		brn	main			;value less than 3 so prompt again
		brzp	fibonacci

;this section of code came from lecture13 under tentimes.asm
multi		ldr	r3, r5, #0		; load r3 with the digit to multiply 
		ld	r2, ascii
		not	r2, r2
		add	r2, r2, #1		;taking x30 away from the value
		add	r3, r3, r2		;to get the number
		add	r3, r3, r3		;times 2
		add	r4, r3, #0		;times 2 now in r4
		add	r3, r3, r3		;times 4
		add	r3, r3, r3		;times 8
		add	r3, r3, r4		;times 10

;adding the second digit to the first digit 
;(which has been timesd by ten)
		add	r5, r5, #1		;move the buffer position back to get second digit
		ldr	r4, r5, #0		;load second digit
		add	r4, r4, r2	
		add	r3, r3, r4		;two digit number now available

;tests to see if value less than 24
		and	r4, r4, #0
		add	r4, r3, #0
		brn	main			;if input negative prompt again
		add	r4, r4, #-10
		add	r4, r4, #-13
		brp	main			;if input over 23 then prompt again

;registers used
;r1 - 
;r2 -
;r3 - counter
;r4 -
;r5 -
;r6 - stack pointer
;r7 - return adress		
;recursive fibonacci function 
fibonacci	

;repurposing registures
;ro - output number
;r1 - number to be subtracted from
;r2 - holds loaded value used to subtract or add
;r3 - subtraction counter 
;r4 - if zero then a digit has been printed
;r5 - not in use
;this code was taken from the lecture13c.asm with my own added comments
print		ld	r2, neg_10000
		add	r1, r0, #0		
		and	r3, r3, #0		
		and	r4, r4, #0		
more_10000	add	r1, r1, r2		;minus 10,000
		brn	end_10000		;value smaller than 10,000
		add	r3, r3, #1		;increment counter
		brnzp	more_10000

end_10000	add	r0, r3, #0		;move counter value to output register
		brz	no_10000		;no value for 10,000 column
		ld	r2, ascii		
		add	r0, r0, r2		;turn output into ascii representation 
		putc				;10,000's digit output
		add	r4, r4, #1		;a digit has been printed 

no_10000	ld	r2, pos_10000		
		add	r1, r1, r2		;restore value to a positve value < 10,000
		ld	r2, neg_1000		;start 1000's column
		and	r3, r3, #0		;clear counter
more_1000	add	r1, r1, r2		
		brn	end_1000		;same as what happened in more_10000
		add	r3, r3, #1		; "              "              "
		brnzp	more_1000		; "              "              "

;printing values between 1 and 9
end_1000	add	r0, r3, #0
		brz	no_1000			;if counter zero then no value for 1000's column
		ld	r2, ascii		;need to check if a digit has been printed yet
		add	r0, r0, r2
		putc				;print 1000's column value
		add	r4, r4, #1		;show we have printed a digit
		brnzp	do_100

;check if a 0 should be printed
no_1000		add	r4, r4, #0		;check if we have printed a digit yet
		brz	do_100			;if not go onto next column
		ld	r0, ascii		
		putc				;else print a zero

do_100		ld	r2, pos_1000
		add	r1, r1, r2		;restore to positive < 1000
		ld	r2, neg_100
		and	r3, r3, #0
more_100	add	r1, r1, r2		;remaining value to print
		brn	end_100			;no value in 100's column
		add	r3, r3, #1		;increment counter
		brnzp	more_100

;printing values between 1 and 9
end_100		add	r0, r3, #0		;if counter is zero, 
		brz	no_100			;check if a digit has already been printer
		ld	r2, ascii		
		add	r0, r0, r2
		putc				;print 100's column digit
		add	r4, r4, #1		;show that a digit has been printed
		brnzp	do_10			;move on to 10's column

;check if a 0 should be printed
no_100		add	r4, r4, #0	
		brz	do_10			;no digit has been printed before
		ld	r0, ascii
		putc				;else print a zero

do_10		ld	r2, pos_100
		add	r1, r1, r2		;restore to positive number < 100
		and	r3, r3, #0
more_10		add	r1, r1, #-10		;r1 still used to hold number to print
		brn	end_10			
		add	r3, r3, #1		;increment counter
		brnzp	more_10

;printing values between 1 and 9
end_10		add	r0, r3, #0		;no value in 10's column
		brz	no_10			;check if a digit has been printed before
		ld	r2, ascii
		add	r0, r0, r2
		putc				;print 10's column digit
		brnzp	do_1

;check if a 0 should be printed
no_10		add	r4, r4, #0		;if r4 has value of 0
		brz	do_1			;then move on to 0's column
		ld	r0, ascii
		putc				;else print a zero

;no need to check for 0 now so just print the digit
do_1		add	r1, r1, #10		;restore to positive value < 10
		ld	r2, ascii
		add	r0, r1, r2

		putc				;print value

;print a space inbetween numbers of the sequence	
		lea	r0, space
		puts
		brnzp	fibonacci

finish		halt

prompt		.stringz "Enter a number from 3 to 23: "
buffer		.blkw 10
ascii		.fill x0030
space		.stringz " "

neg_10000	.fill -10000
neg_1000	.fill -1000
neg_100		.fill -100
pos_10000	.fill  10000
pos_1000	.fill  1000
pos_100		.fill  100

;stack
bos		.blkw x0080
tos		.fill x0000
		.end