import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FidelitycardComponent } from './fidelitycard.component';

describe('FidelitycardComponent', () => {
  let component: FidelitycardComponent;
  let fixture: ComponentFixture<FidelitycardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FidelitycardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FidelitycardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
