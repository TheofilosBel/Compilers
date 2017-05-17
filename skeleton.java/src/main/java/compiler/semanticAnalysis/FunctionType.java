package compiler.semanticAnalysis;

import java.util.LinkedList;
import java.util.List;

import compiler.node.*;
import compiler.semanticAnalysis.Type;

/*
This is a class to save the info needed for a function
*/
public class FunctionType extends Type {
    
    private String       rettype;
    private LinkedList<PVariable>    argsByRef;
    private LinkedList<PVariable>    argsByVall;
    

    public FunctionType(AType rettype, PFparList argList) {
        super();
        this.rettype = rettype.toString();
        this.argsByRef  = new LinkedList<PVariable>();
        this.argsByVall = new LinkedList<PVariable>();
        
        AVariable var = null;
        PType tempType = null;
        
        /* If the func has arguments clone it else make an empty one */
        if (argList instanceof AExistingFparList) {
            
            /* Get all the arguments and place them in the 2 lists depending on the pass method */
            PFparDef fpar_def = null;
            LinkedList<PVariable> tempList = new LinkedList<PVariable>();
            for(int arg = 0; arg < ((AExistingFparList) argList).getArgs().size() ; arg++) {
                
                fpar_def = ((AExistingFparList) argList).getArgs().get(arg);
                if (fpar_def instanceof AByvallFparDef) {
                    
                    /* Get the PVariable list from the var def */
                    argsByVall.addAll( ((AVarDef) ((AByvallFparDef) fpar_def).getVars()).getVarList() );
                    
                    /* The Variable don't have the type ready , add them from the var_def*/
                    
                    for (int vars = 0; vars < argsByVall.size(); vars++) {
                        var = (AVariable) argsByVall.get(vars);
                        tempType = (PType) ((AType) ((AVarDef) ((AByvallFparDef) fpar_def).getVars()).getType()).clone() ;
                        var.setType(tempType);
                    }
                }
                else if (fpar_def instanceof AByrefFparDef) {
                    
                    /* Get the PVariable list from the var def */

                    argsByRef.addAll( ((AVarDef) ((AByrefFparDef) fpar_def).getVars()).getVarList() );
                }

            } 
        } else {
            this.argsByRef  = null;
            this.argsByVall = null;
        }
        
        
        /* Print the 2 lists */
        System.out.println("Vars by ref in func");
        for (int vars = 0; vars < argsByRef.size(); vars++) {
            var = (AVariable) argsByRef.get(vars);
            System.out.println("-->Name " + var.getId().toString());
            System.out.println("   Type " + var.getType());
        }
        
        System.out.println("Vars by vall in func");
        for (int vars = 0; vars < argsByVall.size(); vars++) {
            var = (AVariable) argsByVall.get(vars);
            System.out.println("-->Name " + var.getId().toString());
            System.out.println("   Type " + var.getType());
        }
        System.out.println();
        
        
        
    }

    public String getType() {
        return this.rettype;
    }

}