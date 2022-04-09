import { Directive, Input } from '@angular/core';

@Directive( {
    selector: '[appHighlight]'
} )

export class Intime {

    @Input() date = new Date();
    
    @Input() inTimeHours= 13;
    
    @Input() showingQstions = 4;
    
}