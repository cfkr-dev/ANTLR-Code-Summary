grammar sourceCode;

source                  :   (IDENTIFIER
                            |CONST_DEF_IDENTIFIER
                            |NUMERIC_INTEGER_CONST
                            |NUMERIC_REAL_CONST
                            |STRING_CONST
                            |COMMENT
                            |REST)+
                        ;

IDENTIFIER              :   MINUS+(MINUS|NUMBER|'_')*
                        |   '_'+(MINUS|NUMBER)+(MINUS|NUMBER|'_')*
                        ;
CONST_DEF_IDENTIFIER    :   MAYUS+(MAYUS|NUMBER|'_')*
                        |   '_'+(MAYUS|NUMBER)+(MAYUS|NUMBER|'_')*
                        ;

NUMERIC_INTEGER_CONST   :   ('+'|'-')?NUMBER+
                        ;
NUMERIC_REAL_CONST      :   (REAL|NUMERIC_INTEGER_CONST)[Ee]NUMERIC_INTEGER_CONST
                        |   REAL
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

REST                    :   . ->skip
                        ;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

fragment MAYUS          :   [A-Z]
                        ;
fragment MINUS          :   [a-z]
                        ;
fragment NUMBER         :   [0-9]
                        ;
fragment REAL           :   ('+'|'-')?(NUMBER*'.'NUMBER+)
                        ;
