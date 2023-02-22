grammar sourceCode;

source                  :   (IDENTIFIER|CONST_DEF_IDENTIFIER|NUMERIC_INTEGER_CONST|NUMERIC_REAL_CONST|STRING_CONST|COMMENT)+
                        ;

IDENTIFIER              :   '_'+(MINUS|NUMBER)+(MINUS|NUMBER|'_')*
                        |   MINUS+(MINUS|NUMBER|'_')*
                        ;
CONST_DEF_IDENTIFIER    :   '_'+(MAYUS|NUMBER)+(MAYUS|NUMBER|'_')*
                        |   MAYUS+(MAYUS|NUMBER|'_')*
                        ;

NUMERIC_INTEGER_CONST   :   ('+'|'-')?NUMBER+
                        ;
NUMERIC_REAL_CONST      :   REAL
                        |   (REAL|NUMERIC_INTEGER_CONST)[Ee]NUMERIC_INTEGER_CONST
                        ;

STRING_CONST            :   '\''(~'\''|('\\''\''))*'\''
                        |   '"'(~'"'|('\\''"'))*'"'
                        ;

COMMENT                 :   '//'~'\n'*
                        |   '/'('*'+)(('*'+)(~[/]+)
                                     |(~[/*]+)
                                     |(~[*]+)('/'+)
                                     |'\n')
                             *('*'+)'/'
                        ;

REST                    :   '\n' ->skip
                        ;


fragment MAYUS          :   [A-Z]
                        ;
fragment MINUS          :   [a-z]
                        ;
fragment NUMBER         :   [0-9]
                        ;
fragment REAL           :   ('+'|'-')?(NUMBER*'.'NUMBER+)
                        ;
