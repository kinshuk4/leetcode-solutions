pub fn answer_queries(nums: Vec<i32>, queries: Vec<i32>) -> Vec<i32> {
    let mut nums = nums; // make nums mutable
    nums.sort();
    let n = nums.len();
    let mut sums = Vec::with_capacity(n + 1);
    sums.push(0);
    for val in &sums {
        print!("{}", val)
    }
    for i in 0..n {
        // sums[i + 1] = sums[i] + nums[i]; 
        sums.push(sums[i] + nums[i]);
    }

    let mut ans = Vec::with_capacity(queries.len());
    for q in queries {
        ans.push(binary_search(& sums, q));
    } 
    return ans;
}

fn binary_search(sum: &Vec<i32>, val: i32) -> i32 {
    let (mut lo, mut hi) = (0, sum.len() - 1);
    
    while lo < hi {
        let mid = hi - (hi - lo) / 2;
        if sum[mid] <= val { 
            lo = mid; 
        }
        else { 
            hi = mid - 1; 
        }
    }
    lo = lo + 1;
    lo as i32
}

#[test]
fn test_answer_queries() {
    assert_eq!(answer_queries(vec![2, 7, 11, 15], vec![3,10,21]), vec![2,3,4]);
}
