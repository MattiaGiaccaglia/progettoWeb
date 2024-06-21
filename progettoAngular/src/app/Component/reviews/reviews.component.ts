import { Component } from '@angular/core';
import { ReviewService } from '../../Service/review/review.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrl: './reviews.component.css'
})
export class ReviewsComponent {
review: any;

constructor(
  private route: ActivatedRoute,
  private reviewService: ReviewService,
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