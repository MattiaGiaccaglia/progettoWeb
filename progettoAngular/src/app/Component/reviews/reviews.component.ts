import { Component } from '@angular/core';
import { ReviewService } from '../../Service/review/review.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrl: './reviews.component.css'
})
export class ReviewsComponent {
review: any;
addingReview: boolean = false;
reviewForm: FormGroup;
private formBuilder: FormBuilder;

constructor(
  private route: ActivatedRoute,
  private reviewService: ReviewService,
  private router: Router,
  private snackBar: MatSnackBar
) { }

ngOnInit(): void {
  this.route.params.subscribe(params => {
    const reviewId = params['id'];
    this.reviewService.getReview(reviewId).subscribe(review => {
      this.review = review;
    });
  });
}
}