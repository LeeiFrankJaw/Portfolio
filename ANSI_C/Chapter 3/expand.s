	.file	"expand.c"
	.def	___main;	.scl	2;	.type	32;	.endef
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB6:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	andl	$-16, %esp
	subl	$2016, %esp
	call	___main
	movl	$1000, 4(%esp)
	leal	1016(%esp), %eax
	movl	%eax, (%esp)
	call	_getstr
	leal	16(%esp), %eax
	movl	%eax, 4(%esp)
	leal	1016(%esp), %eax
	movl	%eax, (%esp)
	call	_expand
	leal	16(%esp), %eax
	movl	%eax, (%esp)
	call	_puts
	movl	$0, %eax
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE6:
	.globl	_expand
	.def	_expand;	.scl	2;	.type	32;	.endef
_expand:
LFB7:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$40, %esp
	movl	$0, -16(%ebp)
	movl	-16(%ebp), %eax
	movl	%eax, -12(%ebp)
	jmp	L4
L10:
	movl	-12(%ebp), %eax
	subl	$2, %eax
	testl	%eax, %eax
	js	L5
	movl	-12(%ebp), %eax
	leal	-1(%eax), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	cmpb	$45, %al
	jne	L5
	movl	-12(%ebp), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	movsbl	%al, %edx
	movl	-12(%ebp), %eax
	leal	-2(%eax), %ecx
	movl	8(%ebp), %eax
	addl	%ecx, %eax
	movzbl	(%eax), %eax
	movsbl	%al, %eax
	movl	%edx, 4(%esp)
	movl	%eax, (%esp)
	call	_isSame
	movl	%eax, -24(%ebp)
	cmpl	$0, -24(%ebp)
	je	L5
	subl	$2, -16(%ebp)
	movl	-12(%ebp), %eax
	leal	-2(%eax), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	movsbl	%al, %eax
	movl	%eax, -20(%ebp)
	jmp	L6
L8:
	movl	-16(%ebp), %eax
	leal	1(%eax), %edx
	movl	%edx, -16(%ebp)
	movl	%eax, %edx
	movl	12(%ebp), %eax
	addl	%eax, %edx
	movl	-20(%ebp), %eax
	movb	%al, (%edx)
	movl	-24(%ebp), %eax
	addl	%eax, -20(%ebp)
L6:
	cmpl	$998, -16(%ebp)
	jg	L7
	movl	-12(%ebp), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	movsbl	%al, %eax
	cmpl	-20(%ebp), %eax
	jne	L8
L7:
	movl	-16(%ebp), %eax
	leal	1(%eax), %edx
	movl	%edx, -16(%ebp)
	movl	%eax, %edx
	movl	12(%ebp), %eax
	addl	%eax, %edx
	movl	-20(%ebp), %eax
	movb	%al, (%edx)
	jmp	L9
L5:
	movl	-16(%ebp), %eax
	leal	1(%eax), %edx
	movl	%edx, -16(%ebp)
	movl	%eax, %edx
	movl	12(%ebp), %eax
	addl	%eax, %edx
	movl	-12(%ebp), %ecx
	movl	8(%ebp), %eax
	addl	%ecx, %eax
	movzbl	(%eax), %eax
	movb	%al, (%edx)
L9:
	addl	$1, -12(%ebp)
L4:
	movl	-12(%ebp), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	testb	%al, %al
	jne	L10
	movl	-16(%ebp), %edx
	movl	12(%ebp), %eax
	addl	%edx, %eax
	movb	$0, (%eax)
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE7:
	.globl	_getstr
	.def	_getstr;	.scl	2;	.type	32;	.endef
_getstr:
LFB8:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$24, %esp
	movl	$0, -12(%ebp)
	jmp	L12
L14:
	movl	-12(%ebp), %edx
	movl	8(%ebp), %eax
	addl	%eax, %edx
	movl	-16(%ebp), %eax
	movb	%al, (%edx)
	addl	$1, -12(%ebp)
L12:
	movl	12(%ebp), %eax
	subl	$1, %eax
	cmpl	-12(%ebp), %eax
	jle	L13
	call	_getchar
	movl	%eax, -16(%ebp)
	cmpl	$-1, -16(%ebp)
	je	L13
	cmpl	$10, -16(%ebp)
	jne	L14
L13:
	movl	-12(%ebp), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movb	$0, (%eax)
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE8:
	.globl	_isupper
	.def	_isupper;	.scl	2;	.type	32;	.endef
_isupper:
LFB9:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$4, %esp
	movl	8(%ebp), %eax
	movb	%al, -4(%ebp)
	cmpb	$64, -4(%ebp)
	jle	L16
	cmpb	$90, -4(%ebp)
	jg	L16
	movl	$1, %eax
	jmp	L17
L16:
	movl	$0, %eax
L17:
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE9:
	.globl	_isLower
	.def	_isLower;	.scl	2;	.type	32;	.endef
_isLower:
LFB10:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$4, %esp
	movl	8(%ebp), %eax
	movb	%al, -4(%ebp)
	cmpb	$96, -4(%ebp)
	jle	L20
	cmpb	$122, -4(%ebp)
	jg	L20
	movl	$1, %eax
	jmp	L21
L20:
	movl	$0, %eax
L21:
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE10:
	.globl	_isDigit
	.def	_isDigit;	.scl	2;	.type	32;	.endef
_isDigit:
LFB11:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$4, %esp
	movl	8(%ebp), %eax
	movb	%al, -4(%ebp)
	cmpb	$47, -4(%ebp)
	jle	L24
	cmpb	$57, -4(%ebp)
	jg	L24
	movl	$1, %eax
	jmp	L25
L24:
	movl	$0, %eax
L25:
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE11:
	.globl	_isSame
	.def	_isSame;	.scl	2;	.type	32;	.endef
_isSame:
LFB12:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$28, %esp
	movl	8(%ebp), %edx
	movl	12(%ebp), %eax
	movb	%dl, -20(%ebp)
	movb	%al, -24(%ebp)
	movsbl	-20(%ebp), %eax
	movl	%eax, (%esp)
	call	_isDigit
	testl	%eax, %eax
	je	L28
	movsbl	-24(%ebp), %eax
	movl	%eax, (%esp)
	call	_isDigit
	testl	%eax, %eax
	jne	L29
L28:
	movsbl	-20(%ebp), %eax
	movl	%eax, (%esp)
	call	_isupper
	testl	%eax, %eax
	je	L30
	movsbl	-24(%ebp), %eax
	movl	%eax, (%esp)
	call	_isupper
	testl	%eax, %eax
	jne	L29
L30:
	movsbl	-20(%ebp), %eax
	movl	%eax, (%esp)
	call	_isLower
	testl	%eax, %eax
	je	L31
	movsbl	-24(%ebp), %eax
	movl	%eax, (%esp)
	call	_isLower
	testl	%eax, %eax
	je	L31
L29:
	movl	$1, %eax
	jmp	L32
L31:
	movl	$0, %eax
L32:
	movl	%eax, -4(%ebp)
	movsbl	-24(%ebp), %edx
	movsbl	-20(%ebp), %eax
	subl	%eax, %edx
	movl	%edx, %eax
	testl	%eax, %eax
	jg	L33
	movl	-4(%ebp), %eax
	negl	%eax
	jmp	L34
L33:
	movl	-4(%ebp), %eax
L34:
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE12:
	.ident	"GCC: (GNU) 4.8.1"
	.def	_puts;	.scl	2;	.type	32;	.endef
	.def	_getchar;	.scl	2;	.type	32;	.endef
