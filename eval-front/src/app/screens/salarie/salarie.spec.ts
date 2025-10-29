import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalarieDetails } from './salarie';

describe('Salarie', () => {
  let component: SalarieDetails;
  let fixture: ComponentFixture<SalarieDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SalarieDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SalarieDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
