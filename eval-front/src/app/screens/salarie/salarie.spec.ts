import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Salarie } from './salarie';

describe('Salarie', () => {
  let component: Salarie;
  let fixture: ComponentFixture<Salarie>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Salarie]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Salarie);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
