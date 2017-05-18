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
        AType tempType = null;
        
        /* If the func has arguments clone it else make an empty one */
        if (argList instanceof AExistingFparList) {
            
            /* Get all the definitions and place them in the 2 lists depending on the pass method */
            PFparDef fpar_def = null;
            for(int def = 0; def < ((AExistingFparList) argList).getArgs().size() ; def++) {
                
                fpar_def = ((AExistingFparList) argList).getArgs().get(def);
                if (fpar_def instanceof AByvallFparDef) {
                    
                    /* Get the PVariable list from the var def */
                    argsByVall.addAll( ((AByvallFparDef) fpar_def).getVarList() );
                    int vars2write = ((AByvallFparDef) fpar_def).getVarList().size();
                    
                    /* The Variable don't have the type ready , add them from the type in fpar_def*/
                    tempType = (AType) (((AByvallFparDef) fpar_def).getType()) ;
                    for (int vars = argsByVall.size() - vars2write ; vars < argsByVall.size(); vars++) {
                        var = (AVariable) argsByVall.get(vars);
                        tempType = (AType) tempType.clone();
                        var.setType((PType) tempType);
                    }

                }
                else if (fpar_def instanceof AByrefFparDef) {
                    
                    /* Get the PVariable list from the var def */
                    argsByRef.addAll( ((AByrefFparDef) fpar_def).getVarList() );
                    int vars2write = ((AByrefFparDef) fpar_def).getVarList().size();
                    
                    /* The Variable don't have the type ready , add them from the type in fpar_def*/
                    tempType = (AType) (((AByrefFparDef) fpar_def).getType()) ;
                    for (int vars = argsByRef.size() - vars2write; vars < argsByRef.size(); vars++) {
                        var = (AVariable) argsByRef.get(vars);
                        tempType = (AType) tempType.clone();
                        var.setType((PType) tempType);
                    }
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